package com.yuebaix.pangu.support;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.core.PanGuCoreConst;
import org.springframework.boot.ResourceBanner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanGuResourceBanner extends ResourceBanner {
    public PanGuResourceBanner(Resource resource) {
        super(resource);
    }

    @Override
    protected List<PropertyResolver> getPropertyResolvers(Environment environment, Class<?> sourceClass) {
        List<PropertyResolver> resolvers = super.getPropertyResolvers(environment, sourceClass);
        resolvers.add(getPanGuResolver());
        return resolvers;
    }

    private PropertyResolver getPanGuResolver() {
        Map<String, Object> panGuBannerEnv = new HashMap<>();
        panGuBannerEnv.put("pangu.author_name", PanGuCoreConst.AUTHOR_NAME);
        panGuBannerEnv.put("pangu.author_email", PanGuCoreConst.AUTHOR_EMAIL);
        panGuBannerEnv.put("pangu.author_github", PanGuCoreConst.AUTHOR_GITHUB);
        panGuBannerEnv.put("pangu.author_gitee", PanGuCoreConst.AUTHOR_GITEE);
        panGuBannerEnv.put("pangu.repo", PanGuCoreConst.REPO);
        panGuBannerEnv.put("pangu-starter.version", getVersionString(PanGuStarterConst.PAN_GU_STARTER_VERSION, false));
        panGuBannerEnv.put("pangu-starter.formatted-version", getVersionString(PanGuStarterConst.PAN_GU_STARTER_VERSION, true));

        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(new MapPropertySource("pangu", panGuBannerEnv));
        return new PropertySourcesPropertyResolver(propertySources);
    }

    private String getVersionString(String version, boolean format) {
        if (version == null) {
            return "";
        }
        return format ? " (v" + version + ")" : version;
    }
}
