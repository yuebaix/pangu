package com.yuebaix.pangu.autoconfigure;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.core.PanGuCoreConst;
import com.yuebaix.pangu.support.PanGuSpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(
        value = PanGuStarterConst.PAN_GU_STARTER_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
@Slf4j
public class PanGuAutoConfiguration {
    @PostConstruct
    public void postInit() {
        PanGuLog.info(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGu Initialized");
    }

    @Bean
    @ConditionalOnMissingBean(PanGuSpringContextHolder.class)
    public PanGuSpringContextHolder panGuSpringContextHolder() {
        return new PanGuSpringContextHolder();
    }
}
