package com.yuebaix.pangu.ready;

import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

import javax.annotation.PostConstruct;
import java.io.IOException;

@ConditionalOnProperty(
        value = PanGuReadyStarterConst.PAN_GU_READY_STARTER_LOGBOOK_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@AutoConfigureBefore(org.zalando.logbook.autoconfigure.LogbookAutoConfiguration.class)
@Slf4j
public class LogbookAutoConfiguration {

    @PostConstruct
    public void postInit() {
        log.info(PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGu Ready Logbook Initialized");
    }

    @Bean
    @ConditionalOnMissingBean(HttpLogWriter.class)
    public PanGuLogbookWriter writer() {
        return new PanGuLogbookWriter();
    }

    @Slf4j
    private static final class PanGuLogbookWriter implements HttpLogWriter {
        @Override
        public void write(Precorrelation precorrelation, String request) throws IOException {
            log.info(request);
        }

        @Override
        public void write(Correlation correlation, String response) throws IOException {
            log.info(response);
        }
    }
}
