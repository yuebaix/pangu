package com.yuebaix.pangu.core;

import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.core.concept.Config;
import com.yuebaix.pangu.core.concept.Context;
import com.yuebaix.pangu.core.concept.Executor;
import com.yuebaix.pangu.core.concept.Input;
import com.yuebaix.pangu.core.concept.Output;
import com.yuebaix.pangu.core.concurrent.PanGuConcurrentKit;
import com.yuebaix.pangu.core.concurrent.PanGuThreadFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

    @Test
    public void testEngine() {
        Config cfg = new Config() {};
        Executor executor = new Executor() {
            @Override
            public void execute(Context ctx) {

            }
        };
        Context context = new Context() {
            @Override
            public void setInput(Input input) {

            }

            @Override
            public Output getOutput() {
                return null;
            }
        };
        Input input = new Input() {};
        context.setInput(input);
        executor.execute(context);
        Output output = context.getOutput();
        System.out.println(output);
    }

    @Test
    @SneakyThrows
    public void testScheduler() {
        ScheduledExecutorService scheduler = PanGuConcurrentKit.scheduler();
        scheduler.scheduleAtFixedRate(() -> PanGuLog.info(log, "scheduler"), 0, 3000, TimeUnit.MILLISECONDS);
        //scheduler.shutdown();
        scheduler.awaitTermination(15000, TimeUnit.MILLISECONDS);
    }
}
