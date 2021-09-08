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
            //regenerate if refresh
            weakPanGuPropertySource.removeAll();
            //springfox-swagger
            if (!PanGuReadyStarterConst.PROPERTY_FALSE.equals(
                    environment.getProperty(PanGuReadyStarterConst.PAN_GU_READY_STARTER_SWAGGER_ENABLED))) {
                weakPanGuPropertySource.putProperty("springfox.documentation.swagger-ui.enabled", "false");
            }
            //logbook
            if (!PanGuReadyStarterConst.PROPERTY_FALSE.equals(
                    environment.getProperty(PanGuReadyStarterConst.PAN_GU_READY_STARTER_LOGBOOK_ENABLED))) {
                weakPanGuPropertySource.putProperty("logbook.include", "/**");
                weakPanGuPropertySource.putProperty("logbook.exclude", "");
                weakPanGuPropertySource.putProperty("logbook.filter.enabled", "true");
                weakPanGuPropertySource.putProperty("logbook.secure-filter.enabled", "true");
                weakPanGuPropertySource.putProperty("logbook.format.style", "http");
                weakPanGuPropertySource.putProperty("logbook.strategy", "default");
                weakPanGuPropertySource.putProperty("logbook.minimum-status", "400");
                weakPanGuPropertySource.putProperty("logbook.obfuscate.headers", "Authorization,Cookie,Set-Cookie");
                weakPanGuPropertySource.putProperty("logbook.obfuscate.parameters", "password,token,mobile,email");
                weakPanGuPropertySource.putProperty("logbook.write.chunk-size", "1000");
                weakPanGuPropertySource.putProperty("pangu.readystarter.logbook.config.obfuscate-body-params", "token,password,mobile,email");
            }
            //inject config here ...
        }
    }
}
/*
# swagger
springfox.documentation.swagger-ui.enabled=false

# logbook
logbook.include=/**
logbook.exclude=
logbook.filter.enabled=true
logbook.secure-filter.enabled=true
logbook.format.style=http
logbook.strategy=default
logbook.minimum-status=400
logbook.obfuscate.headers=Authorization,Cookie,Set-Cookie
logbook.obfuscate.parameters=access_token,password,token,mobile,email
logbook.write.chunk-size=1000
pangu.readystarter.logbook.config.obfuscate-body-params=token,password,mobile,email

 */
