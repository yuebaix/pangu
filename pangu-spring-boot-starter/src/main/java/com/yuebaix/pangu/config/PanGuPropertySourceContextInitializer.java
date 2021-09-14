package com.yuebaix.pangu.config;

import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@Slf4j
public class PanGuPropertySourceContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public static final String PROPERTY_SOURCE_NAME = "pangu.property-source";

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        if (!environment.getPropertySources().contains(PanGuPropertySourceContextInitializer.PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().addLast(new PanGuPropertySource(PanGuPropertySourceContextInitializer.PROPERTY_SOURCE_NAME));
            PanGuLog.info(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PropertySource Initialized");
        }
        PropertySource propertySource = environment.getPropertySources().get(PROPERTY_SOURCE_NAME);
        if (propertySource instanceof PanGuPropertySource) {
            PanGuPropertySource panGuPropertySource = (PanGuPropertySource) propertySource;
            //inject config here ...
        }
    }
}
