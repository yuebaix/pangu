package com.yuebaix.pangu.ready;

import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class PanGuReadyAutoConfiguration {
    @PostConstruct
    public void postInit() {
        log.info(PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGuReady Initialized");
    }
}
