package com.yuebaix.pangu.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class PanGuLogTest {
    @Test
    public void testLog() {
        PanGuLog.trace(log, "testMsg");
        PanGuLog.trace(log, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.trace(log, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.debug(log, "testMsg");
        PanGuLog.debug(log, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.debug(log, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.info(log, "testMsg");
        PanGuLog.info(log, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.info(log, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.warn(log, "testMsg");
        PanGuLog.warn(log, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.warn(log, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.error(log, "testMsg");
        PanGuLog.error(log, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.error(log, "testFmt {} {} {}", "1", "2", "3");
    }

    @Test
    public void testNoLog() {
        PanGuLog.trace(null, "testMsg");
        PanGuLog.trace(null, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.trace(null, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.debug(null, "testMsg");
        PanGuLog.debug(null, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.debug(null, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.info(null, "testMsg");
        PanGuLog.info(null, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.info(null, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.warn(null, "testMsg");
        PanGuLog.warn(null, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.warn(null, "testFmt {} {} {}", "1", "2", "3");
        PanGuLog.error(null, "testMsg");
        PanGuLog.error(null, "testEx", new IllegalArgumentException("test exception"));
        PanGuLog.error(null, "testFmt {} {} {}", "1", "2", "3");
    }
}
