package com.tn.shell.audit;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GestatTraceAspect {

    private static final Logger LOG = Logger.getLogger(GestatTraceAspect.class.getName());

    @Value("${trace.gestat.enabled:true}")
    private boolean enabled;

    @Around("execution(* com.tn.shell.service.gestat..*(..)) || execution(* com.tn.shell.dao.gestat..*(..))")
    public Object traceGestatLayers(ProceedingJoinPoint pjp) throws Throwable {
        if (!enabled) {
            return pjp.proceed();
        }
        long start = System.currentTimeMillis();
        String signature = pjp.getSignature().toShortString();
        LOG.log(Level.INFO, "[GESTAT-TRACE] -> {0} args={1}", new Object[] { signature, summarizeArgs(pjp.getArgs()) });
        try {
            Object result = pjp.proceed();
            long elapsed = System.currentTimeMillis() - start;
            LOG.log(Level.INFO, "[GESTAT-TRACE] <- {0} result={1} elapsedMs={2}",
                    new Object[] { signature, summarizeValue(result), elapsed });
            return result;
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - start;
            LOG.log(Level.SEVERE, "[GESTAT-TRACE] !! " + signature + " failed after " + elapsed + "ms", ex);
            throw ex;
        }
    }

    private String summarizeArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(summarizeValue(args[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private String summarizeValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Collection<?>) {
            return value.getClass().getSimpleName() + "(size=" + ((Collection<?>) value).size() + ")";
        }
        if (value instanceof Map<?, ?>) {
            return value.getClass().getSimpleName() + "(size=" + ((Map<?, ?>) value).size() + ")";
        }
        if (value instanceof String) {
            String s = (String) value;
            if (s.length() > 80) {
                s = s.substring(0, 80) + "...";
            }
            return "String(\"" + s + "\")";
        }
        if (value instanceof Number || value instanceof Boolean || value instanceof Character) {
            return value.getClass().getSimpleName() + "(" + value + ")";
        }
        return value.getClass().getSimpleName();
    }
}

