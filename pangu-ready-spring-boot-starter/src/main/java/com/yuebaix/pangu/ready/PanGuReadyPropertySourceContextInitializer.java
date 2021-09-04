package com.yuebaix.pangu.ready;

import com.yuebaix.pangu.config.PanGuPropertySource;
import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@Slf4j
public class PanGuReadyPropertySourceContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public static final String WEAK_PROPERTY_SOURCE_NAME = "pangu.ready.weak-property-source";
    public static final String STRONG_PROPERTY_SOURCE_NAME = "pangu.ready.strong-property-source";

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        if (!environment.getPropertySources().contains(STRONG_PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().addFirst(new PanGuPropertySource(STRONG_PROPERTY_SOURCE_NAME));
            log.info(PanGuCoreConst.PAN_GU_TRACE_PREFIX + "StrongPropertySource Initialized");
        }
        PropertySource strongPropertySource = environment.getPropertySources().get(STRONG_PROPERTY_SOURCE_NAME);
        if (strongPropertySource instanceof PanGuPropertySource) {
            PanGuPropertySource strongPanGuPropertySource = (PanGuPropertySource) strongPropertySource;
            //inject config here ...
        }
        if (!environment.getPropertySources().contains(WEAK_PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().addLast(new PanGuPropertySource(WEAK_PROPERTY_SOURCE_NAME));
            log.info(PanGuCoreConst.PAN_GU_TRACE_PREFIX + "WeakPropertySource Initialized");
        }
        PropertySource weakPropertySource = environment.getPropertySources().get(WEAK_PROPERTY_SOURCE_NAME);
        if (weakPropertySource instanceof PanGuPropertySource) {
            PanGuPropertySource weakPanGuPropertySource = (PanGuPropertySource) weakPropertySource;
            weakPanGuPropertySource.putProperty("springfox.documentation.swagger-ui.enabled", "false");
            //inject config here ...
        }
    }
}
