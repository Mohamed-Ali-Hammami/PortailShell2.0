package com.tn.shell.audit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersistenceWriteGuardAspect {

    private static final Logger LOG = Logger.getLogger(PersistenceWriteGuardAspect.class.getName());

    @Autowired
    private NotNullConstraintRegistry constraintRegistry;

    @Value("${guard.notnull.failfast:true}")
    private boolean failFast;

    @Before("execution(* com.tn.shell.dao..*.save*(..))"
            + " || execution(* com.tn.shell.dao..*.update*(..))"
            + " || execution(* com.tn.shell.dao..*.add*(..))"
            + " || execution(* com.tn.shell.dao..*.create*(..))"
            + " || execution(* com.tn.shell.service..*.save*(..))"
            + " || execution(* com.tn.shell.service..*.update*(..))"
            + " || execution(* com.tn.shell.service..*.add*(..))"
            + " || execution(* com.tn.shell.service..*.create*(..))")
    public void validateBeforeWrite(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            Class<?> entityClass = getEntityClass(arg.getClass());
            if (entityClass == null) {
                continue;
            }
            validateEntity(jp, arg, entityClass);
        }
    }

    private void validateEntity(JoinPoint jp, Object entity, Class<?> entityClass) {
        String tableName = resolveTableName(entityClass);
        Set<String> requiredColumns = constraintRegistry.requiredColumnsForTable(tableName);
        if (requiredColumns.isEmpty()) {
            return;
        }

        Map<String, Object> valuesByColumn = new HashMap<String, Object>();
        Set<String> mappedColumns = new HashSet<String>();
        extractColumns(entity, entityClass, valuesByColumn, mappedColumns);

        List<String> nullColumns = new ArrayList<String>();
        List<String> unmappedColumns = new ArrayList<String>();
        for (String required : requiredColumns) {
            if (!mappedColumns.contains(required)) {
                unmappedColumns.add(required);
                continue;
            }
            if (valuesByColumn.get(required) == null) {
                nullColumns.add(required);
            }
        }

        if (nullColumns.isEmpty() && unmappedColumns.isEmpty()) {
            return;
        }

        String method = jp.getSignature().toShortString();
        String message = "Persistence guard blocked " + method + " entity=" + entityClass.getSimpleName()
                + " table=" + tableName + " nullRequired=" + nullColumns + " unmappedRequired=" + unmappedColumns;

        if (failFast) {
            throw new IllegalArgumentException(message);
        }
        LOG.warning(message);
    }

    private void extractColumns(Object instance, Class<?> type, Map<String, Object> valuesByColumn, Set<String> mappedColumns) {
        if (instance == null || type == null) {
            return;
        }
        Class<?> cursor = type;
        while (cursor != null && cursor != Object.class) {
            Field[] fields = cursor.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (field.isAnnotationPresent(Transient.class)) {
                    continue;
                }
                if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
                    continue;
                }
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(instance);
                } catch (Exception ignore) {
                }

                if (field.isAnnotationPresent(Embedded.class)) {
                    if (value != null) {
                        extractColumns(value, field.getType(), valuesByColumn, mappedColumns);
                    } else {
                        registerEmbeddedColumns(field.getType(), mappedColumns, valuesByColumn);
                    }
                    continue;
                }

                String explicit = explicitColumnName(field);
                String base = explicit != null ? explicit : field.getName();
                registerColumn(base, value, mappedColumns, valuesByColumn);
                if (explicit == null) {
                    registerColumn(toSnake(base), value, mappedColumns, valuesByColumn);
                }
            }
            cursor = cursor.getSuperclass();
        }
    }

    private void registerEmbeddedColumns(Class<?> embeddedType, Set<String> mappedColumns, Map<String, Object> valuesByColumn) {
        if (embeddedType == null) {
            return;
        }
        Field[] fields = embeddedType.getDeclaredFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers()) || f.isAnnotationPresent(Transient.class)) {
                continue;
            }
            String explicit = explicitColumnName(f);
            String base = explicit != null ? explicit : f.getName();
            registerColumn(base, null, mappedColumns, valuesByColumn);
            if (explicit == null) {
                registerColumn(toSnake(base), null, mappedColumns, valuesByColumn);
            }
        }
    }

    private void registerColumn(String col, Object value, Set<String> mappedColumns, Map<String, Object> valuesByColumn) {
        if (col == null || col.trim().isEmpty()) {
            return;
        }
        String n = norm(col);
        mappedColumns.add(n);
        if (!valuesByColumn.containsKey(n)) {
            valuesByColumn.put(n, value);
        }
    }

    private String explicitColumnName(Field field) {
        Column c = field.getAnnotation(Column.class);
        if (c != null && c.name() != null && !c.name().trim().isEmpty()) {
            return c.name();
        }
        JoinColumn j = field.getAnnotation(JoinColumn.class);
        if (j != null && j.name() != null && !j.name().trim().isEmpty()) {
            return j.name();
        }
        if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
            return field.getName() + "id";
        }
        return null;
    }

    private String resolveTableName(Class<?> entityClass) {
        Table t = entityClass.getAnnotation(Table.class);
        if (t != null && t.name() != null && !t.name().trim().isEmpty()) {
            return t.name();
        }
        return entityClass.getSimpleName();
    }

    private Class<?> getEntityClass(Class<?> type) {
        if (type == null) {
            return null;
        }
        Class<?> c = type;
        while (c != null && c != Object.class) {
            if (c.isAnnotationPresent(Entity.class)) {
                return c;
            }
            c = c.getSuperclass();
        }
        return null;
    }

    private String toSnake(String name) {
        return name.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase(Locale.ROOT);
    }

    private String norm(String s) {
        return s == null ? null : s.trim().toLowerCase(Locale.ROOT);
    }
}

