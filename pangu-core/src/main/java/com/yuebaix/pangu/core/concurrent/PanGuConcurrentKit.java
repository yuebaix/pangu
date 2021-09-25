package com.yuebaix.pangu.core.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class PanGuConcurrentKit {
    private PanGuConcurrentKit() {}

    private static final String SCHEDULER = "scheduler";
    private static final char[] scheduledExecutorServiceMonitor = new char[0];
    private volatile static ScheduledExecutorService scheduledExecutorService = null;

    public static ThreadFactory newThreadFactory(String threadGroupName) {
        return new PanGuThreadFactory(threadGroupName);
    }

    public static ThreadFactory newThreadFactory(String threadGroupName, boolean daemon, int priority) {
        return new PanGuThreadFactory(threadGroupName, daemon, priority);
    }

    public static ScheduledExecutorService scheduler() {
        if (scheduledExecutorService == null) {
            synchronized (scheduledExecutorServiceMonitor) {
                if (scheduledExecutorService == null) {
                    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(newThreadFactory(SCHEDULER, true, -1));
                }
            }
        }
        return scheduledExecutorService;
    }
}
