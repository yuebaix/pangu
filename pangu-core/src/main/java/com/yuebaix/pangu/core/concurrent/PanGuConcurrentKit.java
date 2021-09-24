package com.yuebaix.pangu.core.concurrent;

import java.util.concurrent.ThreadFactory;

public class PanGuConcurrentKit {
    private PanGuConcurrentKit() {}

    public static ThreadFactory newThreadFactory(String threadGroupName) {
        return new PanGuThreadFactory(threadGroupName);
    }

    public static ThreadFactory newThreadFactory(String threadGroupName, boolean daemon, int priority) {
        return new PanGuThreadFactory(threadGroupName, daemon, priority);
    }
}
