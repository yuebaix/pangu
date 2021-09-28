package com.yuebaix.pangu.common.util;

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
        String ip = toml.getString("ip");
        System.out.println(ip);
        Long port = toml.getLong("port");
        System.out.println(port);
        FakeConfig fakeConfig = toml.to(FakeConfig.class);
        System.out.println(JacksonUtil.write(fakeConfig));
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
