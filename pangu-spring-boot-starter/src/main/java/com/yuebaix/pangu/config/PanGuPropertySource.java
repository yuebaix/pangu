package com.yuebaix.pangu.config;

import org.springframework.core.env.PropertySource;
import java.util.HashMap;
import java.util.Map;

public class PanGuPropertySource extends PropertySource {
    public static final String PROPERTY_SOURCE_NAME = "pangu.property-source";
    private Map<String, Object> properties = new HashMap<>();

    public PanGuPropertySource() {
        super(PROPERTY_SOURCE_NAME);
        //inject config ...
    }

    @Override
    public Object getProperty(String name) {
        return this.properties.get(name);
    }
}
