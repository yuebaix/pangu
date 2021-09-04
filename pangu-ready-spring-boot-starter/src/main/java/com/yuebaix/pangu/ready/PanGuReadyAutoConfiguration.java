package com.yuebaix.pangu.ready;

import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(
        value = PanGuReadyStarterConst.PAN_GU_READY_STARTER_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
@Import({SwaggerAutoConfiguration.class})
@Slf4j
public class PanGuReadyAutoConfiguration {
    @PostConstruct
    public void postInit() {
        log.info(PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGu Ready Initialized");
    }
}
