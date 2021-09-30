package com.yuebaix.pangu.common.util;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;
import com.moandjiezana.toml.Toml;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.InputStream;

public class TomlUtilTest {

    @Test
    @SneakyThrows
    public void testToml() {
        InputStream inputStream = ClassUtil.getDefaultClassLoader().getResourceAsStream("test/fakeconfig.toml");
        Toml toml = new Toml().read(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        FakeConfig fakeConfig = toml.to(FakeConfig.class);
        System.out.println(JacksonUtil.write(fakeConfig));
    }

    @Test
    @SneakyThrows
    public void testProperties() {
        InputStream inputStream = ClassUtil.getDefaultClassLoader().getResourceAsStream("test/fakeconfig.properties");

        JavaPropsMapper mapper = PropertiesUtil.getJavaPropsMapperHolder();
        JavaPropsSchema schema = JavaPropsSchema.emptySchema()
                .withPrefix("config");

        FakeConfig result = mapper.readerFor(FakeConfig.class)
                .with(schema)
                .readValue(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }

        String props = mapper.writerFor(FakeConfig.class)
                .writeValueAsString(result);
        System.out.println(props);
    }

    @Data
    private static final class FakeConfig {
        private String ip;
        private Integer port;
        private FakeUser user;
    }

    @Data
    private static final class FakeUser {
        private String name;
        private Integer age;
    }
}
