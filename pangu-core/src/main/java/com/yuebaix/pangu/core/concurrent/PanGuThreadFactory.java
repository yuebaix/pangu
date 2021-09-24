package com.yuebaix.pangu.core.concurrent;

import com.yuebaix.pangu.common.util.StringUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PanGuThreadFactory implements ThreadFactory {
    private static final String EMPTY_STR = "";
    private static final String THREAD_NAME_SP = "-";
    private static final String DEFAULT_THREAD_GROUP_NAME = "PanGu";

    private final ThreadGroup threadGroup;
    private final String threadNamePrefix;
    private final boolean daemon;
    private final int priority;

    private static final AtomicInteger threadPoolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public PanGuThreadFactory() {
        this(null, null, false, -1);
    }

    public PanGuThreadFactory(String threadGroupName) {
        this(threadGroupName, null, false, -1);
    }

    public PanGuThreadFactory(String threadGroupName, boolean daemon, int priority) {
        this(threadGroupName, null, daemon, priority);
    }

    public PanGuThreadFactory(String threadGroupName, String threadNamePrefix) {
        this(threadGroupName, threadNamePrefix, false, -1);
    }

    public PanGuThreadFactory(String threadGroupName, String threadNamePrefix, boolean daemon, int priority) {
        if (StringUtil.isEmpty(threadGroupName)) {
            threadGroupName = DEFAULT_THREAD_GROUP_NAME;
        }
        if (StringUtil.isEmpty(threadNamePrefix)) {
            threadNamePrefix = String.join(THREAD_NAME_SP,
                    threadGroupName,
                    EMPTY_STR + threadPoolNumber.getAndIncrement(),
                    //this.getClass().getSimpleName(),
                    EMPTY_STR);
        }
        this.threadGroup = new ThreadGroup(threadGroupName);
        this.threadNamePrefix = threadNamePrefix;
        this.daemon = daemon;
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, threadNamePrefix + threadNumber.getAndIncrement(), 0);
        if (daemon) {
            if (!t.isDaemon()) {
                t.setDaemon(true);
            }
        } else {
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
        }
        if (priority > 0 && t.getPriority() != priority) {
            t.setPriority(priority);
        }
        return t;
    }
}
