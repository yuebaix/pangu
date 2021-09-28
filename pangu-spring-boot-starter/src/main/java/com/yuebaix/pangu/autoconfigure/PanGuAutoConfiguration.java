package com.yuebaix.pangu.autoconfigure;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.autoconfigure.optimize.OptimizeAutoConfiguration;
import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(
        value = PanGuStarterConst.PAN_GU_STARTER_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
@Import({OptimizeAutoConfiguration.class})
@Slf4j
public class PanGuAutoConfiguration {
    @PostConstruct
    public void postInit() {
        PanGuLog.info(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGu Initialized");
    }
}
