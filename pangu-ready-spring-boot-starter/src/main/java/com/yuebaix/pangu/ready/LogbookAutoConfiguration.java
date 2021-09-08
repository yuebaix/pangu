package com.yuebaix.pangu.ready;

import com.yuebaix.pangu.core.PanGuCoreConst;
import com.yuebaix.pangu.ready.logbook.PanGuLogbookProperties;
import com.yuebaix.pangu.ready.logbook.PanGuLogbookWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.HttpLogWriter;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(
        value = PanGuReadyStarterConst.PAN_GU_READY_STARTER_LOGBOOK_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@AutoConfigureBefore(org.zalando.logbook.autoconfigure.LogbookAutoConfiguration.class)
@EnableConfigurationProperties(PanGuLogbookProperties.class)
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
}
