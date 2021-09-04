package com.yuebaix.pangu.config;

import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PanGuPropertySource extends PropertySource {
    private Map<String, Object> properties = new HashMap<>();

    public PanGuPropertySource(String name) {
        super(name);
        //inject config here ...
    }

    @Override
    public Object getProperty(String name) {
        return this.properties.get(name);
    }

    public void putProperty(String name, String value) {
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
            properties.put(name, value);
        }
    }
}
