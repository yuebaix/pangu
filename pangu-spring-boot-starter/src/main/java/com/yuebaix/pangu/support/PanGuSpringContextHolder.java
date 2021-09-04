package com.yuebaix.pangu.support;

import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import java.util.Map;

@Slf4j
public class PanGuSpringContextHolder implements ApplicationContextAware, EnvironmentAware {
    private ApplicationContext context;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.debug(this.getClass().getSimpleName() + PanGuCoreConst.TRACE_ARROW + "Environment Injected");
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    public ApplicationContext getApplicationContext() {
        return this.context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
        log.debug(this.getClass().getSimpleName() + PanGuCoreConst.TRACE_ARROW + "ApplicationContext Injected");
    }

    public <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return context.getBeansOfType(clazz);
    }

    public Object getBean(String name) {
        return context.getBean(name);
    }

    public <T> T getBean(Class<T> clazz) {
        T bean = context.getBean(clazz);
        return bean;
    }

    public void publishEvent(ApplicationEvent event) {
        context.publishEvent(event);
    }
}

