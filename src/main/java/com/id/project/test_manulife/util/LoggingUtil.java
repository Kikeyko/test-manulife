package com.id.project.test_manulife.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.MDC;

@Slf4j
@UtilityClass
public class LoggingUtil {

    // Log Request dari Frontend / Controller Client
    public void logRequestFE(String api, String msg) {
        MDC.put("sourceType", "REQ_FE");
        MDC.put("flow", api);
        MDC.put("duration", "");
        log.info(sanitizeString(msg));
    }

    // Log Response balik ke Frontend / Controller Client
    public void logResponseFE(String api, String msg, DateTime start) {
        MDC.put("sourceType", "RES_FE");
        MDC.put("flow", api);
        MDC.put("duration", String.valueOf(new Duration(start, new DateTime()).getMillis()));
        log.info(sanitizeString(msg));
    }

    // Log Request Internal Service
    public void logRequest(String flow, String msg) {
        MDC.put("sourceType", "REQ");
        MDC.put("flow", flow);
        MDC.put("duration", "");
        log.info(sanitizeString(msg));
    }

    // Log Response Internal Service
    public void logResponse(String flow, String msg, DateTime start) {
        MDC.put("sourceType", "RES");
        MDC.put("flow", flow);
        MDC.put("duration", String.valueOf(new Duration(start, new DateTime()).getMillis()));
        log.info(sanitizeString(msg));
    }

    // Log Error
    public void logError(String flow, String msg, Throwable e, DateTime start) {
        MDC.put("sourceType", "ERROR");
        MDC.put("flow", flow);
        MDC.put("duration", String.valueOf(new Duration(start, new DateTime()).getMillis()));
        log.error("{} - Exception: {}", sanitizeString(msg), e != null ? e.getMessage() : "N/A");
    }

    private String sanitizeString(String str) {
        return str == null ? "" : str.replaceAll("\\s+", " ");
    }
}