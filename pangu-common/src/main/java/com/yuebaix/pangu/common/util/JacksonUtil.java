package com.yuebaix.pangu.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

public final class JacksonUtil {
    private static final ObjectMapper objectMapperHolder;
    static {
        ObjectMapper ob = new ObjectMapper();
        //允许序列化空POJO
        ob.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //把时间按照字符串输出
        ob.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //POJO中的null值不输出
        ob.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //在遇到未知属性的时候不抛出异常
        ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapperHolder = ob;
    }
    private JacksonUtil() {}

    public static ObjectMapper getObjectMapperHolder() {
        return objectMapperHolder;
    }

    @SneakyThrows
    public static String write(Object obj) {
        return getObjectMapperHolder().writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T read(String cnt, Class<T> clazz) {
        return getObjectMapperHolder().readValue(cnt, clazz);
    }

    @SneakyThrows
    public static <T> T read(String cnt, TypeReference<T> typeRef) {
        return getObjectMapperHolder().readValue(cnt, typeRef);
    }
}
