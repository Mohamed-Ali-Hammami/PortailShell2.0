package com.tn.shell.audit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotNullConstraintRegistry {

    private static final Logger LOG = Logger.getLogger(NotNullConstraintRegistry.class.getName());

    @Autowired
    private DataSource dataSource;

    private final Map<String, Set<String>> requiredColumnsByTable = new HashMap<String, Set<String>>();

    @PostConstruct
    public void init() {
        load();
    }

    public Set<String> requiredColumnsForTable(String tableName) {
        if (tableName == null) {
            return Collections.emptySet();
        }
        Set<String> cols = requiredColumnsByTable.get(norm(tableName));
        if (cols == null) {
            return Collections.emptySet();
        }
        return cols;
    }

    private void load() {
        requiredColumnsByTable.clear();
        try (Connection c = dataSource.getConnection()) {
            String schema = currentSchema(c);
            if (schema == null || schema.trim().isEmpty()) {
                LOG.warning("NotNullConstraintRegistry: unable to resolve current schema, guard disabled.");
                return;
            }

            String sql = "SELECT TABLE_NAME, COLUMN_NAME "
                    + "FROM information_schema.COLUMNS "
                    + "WHERE TABLE_SCHEMA = ? "
                    + "AND IS_NULLABLE = 'NO' "
                    + "AND COLUMN_DEFAULT IS NULL "
                    + "AND EXTRA NOT LIKE '%auto_increment%'";

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, schema);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String table = rs.getString(1);
                        String column = rs.getString(2);
                        String t = norm(table);
                        Set<String> cols = requiredColumnsByTable.get(t);
                        if (cols == null) {
                            cols = new HashSet<String>();
                            requiredColumnsByTable.put(t, cols);
                        }
                        cols.add(norm(column));
                    }
                }
            }

            LOG.info("NotNullConstraintRegistry: loaded NOT NULL constraints for " + requiredColumnsByTable.size()
                    + " tables from schema=" + schema);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "NotNullConstraintRegistry load failed; guard checks may be incomplete.", e);
        }
    }

    private String currentSchema(Connection c) {
        try {
            DatabaseMetaData md = c.getMetaData();
            String url = md.getURL();
            if (url != null) {
                int slash = url.lastIndexOf('/');
                if (slash >= 0 && slash + 1 < url.length()) {
                    String tail = url.substring(slash + 1);
                    int q = tail.indexOf('?');
                    return q >= 0 ? tail.substring(0, q) : tail;
                }
            }
        } catch (Exception ignore) {
        }
        try (PreparedStatement ps = c.prepareStatement("SELECT DATABASE()"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception ignore) {
        }
        return null;
    }

    private String norm(String s) {
        return s == null ? null : s.trim().toLowerCase(Locale.ROOT);
    }
}

