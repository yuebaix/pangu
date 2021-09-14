package com.yuebaix.pangu.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class PanGuLog {
    private PanGuLog() {}

    public static void trace(Logger logger, String s) {
        logger = logger == null ? log : logger;
        if (logger.isTraceEnabled()) {
            logger.trace(s);
        }
    }

    public static void trace(Logger logger, String s, Throwable e) {
        logger = logger == null ? log : logger;
        if (logger.isTraceEnabled()) {
            logger.trace(s, e);
        }
    }

    public static void trace(Logger logger, String s, Object... o) {
        logger = logger == null ? log : logger;
        if (logger.isTraceEnabled()) {
            logger.trace(s, o);
        }
    }

    public static void debug(Logger logger, String s) {
        logger = logger == null ? log : logger;
        if (logger.isDebugEnabled()) {
            logger.debug(s);
        }
    }

    public static void debug(Logger logger, String s, Throwable e) {
        logger = logger == null ? log : logger;
        if (logger.isDebugEnabled()) {
            logger.debug(s, e);
        }
    }

    public static void debug(Logger logger, String s, Object... o) {
        logger = logger == null ? log : logger;
        if (logger.isDebugEnabled()) {
            logger.debug(s, o);
        }
    }

    public static void info(Logger logger, String s) {
        logger = logger == null ? log : logger;
        if (logger.isInfoEnabled()) {
            logger.info(s);
        }
    }

    public static void info(Logger logger, String s, Throwable e) {
        logger = logger == null ? log : logger;
        if (logger.isInfoEnabled()) {
            logger.info(s, e);
        }
    }

    public static void info(Logger logger, String s, Object... o) {
        logger = logger == null ? log : logger;
        if (logger.isInfoEnabled()) {
            logger.info(s, o);
        }
    }

    public static void warn(Logger logger, String s) {
        logger = logger == null ? log : logger;
        if (logger.isWarnEnabled()) {
            logger.warn(s);
        }
    }

    public static void warn(Logger logger, String s, Throwable e) {
        logger = logger == null ? log : logger;
        if (logger.isWarnEnabled()) {
            logger.warn(s, e);
        }
    }

    public static void warn(Logger logger, String s, Object... o) {
        logger = logger == null ? log : logger;
        if (logger.isWarnEnabled()) {
            logger.warn(s, o);
        }
    }

    public static void error(Logger logger, String s) {
        logger = logger == null ? log : logger;
        if (logger.isErrorEnabled()) {
            logger.error(s);
        }
    }

    public static void error(Logger logger, String s, Throwable e) {
        logger = logger == null ? log : logger;
        if (logger.isErrorEnabled()) {
            logger.error(s, e);
        }
    }

    public static void error(Logger logger, String s, Object... o) {
        logger = logger == null ? log : logger;
        if (logger.isErrorEnabled()) {
            logger.error(s, o);
        }
    }
}
