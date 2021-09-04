package com.yuebaix.pangu.autoconfigure;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.support.PanGuSpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(
        value = PanGuStarterConst.PANGU_STARTER_ENABLED,
        havingValue = "true",
        matchIfMissing = true
)
@Configuration
public class PanGuAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(PanGuSpringContextHolder.class)
    public PanGuSpringContextHolder panGuSpringContextHolder() {
        return new PanGuSpringContextHolder();
    }
}
