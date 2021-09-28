package com.yuebaix.pangu.autoconfigure.optimize;

import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.support.PanGuSpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty(
        value = PanGuStarterConst.PAN_GU_STARTER_OPTIMIZE_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Slf4j
public class OptimizeAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(PanGuSpringContextHolder.class)
    @ConditionalOnProperty(value = PanGuStarterConst.PAN_GU_STARTER_OPTIMIZE_CONTEXT_HOLDER_ENABLED, havingValue = "true", matchIfMissing = true)
    public PanGuSpringContextHolder panGuSpringContextHolder() {
        return new PanGuSpringContextHolder();
    }

    @Bean
    @ConditionalOnMissingBean(AfterburnerModule.class)
    @ConditionalOnProperty(value = PanGuStarterConst.PAN_GU_STARTER_OPTIMIZE_AFTERBURNER_ENABLED, havingValue = "true", matchIfMissing = true)
    public AfterburnerModule afterburnerModule() {
        return new AfterburnerModule();
    }
}
