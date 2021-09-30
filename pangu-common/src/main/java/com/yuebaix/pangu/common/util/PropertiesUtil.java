package com.yuebaix.pangu.common.util;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

public class PropertiesUtil {
    private static final JavaPropsMapper javaPropsMapperHolder;
    static {
        JavaPropsMapper jpm = new JavaPropsMapper();
        javaPropsMapperHolder = jpm;
    }
    private PropertiesUtil() {}

    public static JavaPropsMapper getJavaPropsMapperHolder() {
        return javaPropsMapperHolder;
    }
}
