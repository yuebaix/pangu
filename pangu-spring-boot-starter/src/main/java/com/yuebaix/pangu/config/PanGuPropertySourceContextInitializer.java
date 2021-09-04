package com.yuebaix.pangu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class PanGuPropertySourceContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        if (!environment.getPropertySources().contains(PanGuPropertySource.PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().addLast(new PanGuPropertySource());
            log.info(this.getClass().getSimpleName() + "--->PropertySource Initialized");
        }
    }
}
