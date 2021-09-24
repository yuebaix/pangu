package com.yuebaix.pangu.core;

import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.core.concurrent.PanGuThreadFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PanGuTest {

    @Test
    @SneakyThrows
    public void testThreadFactory() {
        PanGuThreadFactory panGuThreadFactory = new PanGuThreadFactory();
        ExecutorService executorService = Executors.newSingleThreadExecutor(panGuThreadFactory);
        executorService.execute(() -> PanGuLog.info(log, "working on!"));
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }

    @Test
    @SneakyThrows
    public void testThreadFactoryWithName() {
        PanGuThreadFactory panGuThreadFactory = new PanGuThreadFactory("GroupName");
        ExecutorService executorService = Executors.newSingleThreadExecutor(panGuThreadFactory);
        executorService.execute(() -> PanGuLog.info(log, "working on!"));
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }

    @Test
    @SneakyThrows
    public void testThreadFactoryWithPriority() {
        PanGuThreadFactory panGuThreadFactory = new PanGuThreadFactory("GroupName", true, 6);
        ExecutorService executorService = Executors.newSingleThreadExecutor(panGuThreadFactory);
        executorService.execute(() -> PanGuLog.info(log, "working on!"));
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }

    @Test
    @SneakyThrows
    public void testThreadFactoryWithNameAndPrefix() {
        PanGuThreadFactory panGuThreadFactory = new PanGuThreadFactory("GroupName", "GroupName-ThreadNamePrefix-");
        ExecutorService executorService = Executors.newSingleThreadExecutor(panGuThreadFactory);
        executorService.execute(() -> PanGuLog.info(log, "working on!"));
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }

    @Test
    @SneakyThrows
    public void testThreadFactoryWithAll() {
        PanGuThreadFactory panGuThreadFactory = new PanGuThreadFactory("GroupName", "GroupName-ThreadNamePrefix-", true, 6);
        ExecutorService executorService = Executors.newSingleThreadExecutor(panGuThreadFactory);
        executorService.execute(() -> PanGuLog.info(log, "working on!"));
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }

    @Test
    @SneakyThrows
    public void testMultiThreadFactory() {
        PanGuThreadFactory panGuBossThreadFactory = new PanGuThreadFactory("PanGuBossGroup");
        ExecutorService bossExecutorService = Executors.newSingleThreadExecutor(panGuBossThreadFactory);
        bossExecutorService.execute(() -> PanGuLog.info(log, "1 working on!"));

        PanGuThreadFactory panGuWorkerThreadFactory = new PanGuThreadFactory("PanGuWorkerGroup");
        ExecutorService workerExecutorService = Executors.newSingleThreadExecutor(panGuWorkerThreadFactory);
        workerExecutorService.execute(() -> PanGuLog.info(log, "2 working on!"));

        System.out.println("hold on!");

        bossExecutorService.shutdown();
        bossExecutorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        workerExecutorService.shutdown();
        workerExecutorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
    }
}
