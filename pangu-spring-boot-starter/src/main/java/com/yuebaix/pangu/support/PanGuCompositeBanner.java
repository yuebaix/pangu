package com.yuebaix.pangu.support;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.core.PanGuCoreConst;
import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanGuCompositeBanner implements Banner {
    private final List<Banner> banners = new ArrayList<>();

    public PanGuCompositeBanner() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource springbootResource = resourceLoader.getResource(PanGuStarterConst.SPRING_BOOT_BANNER_URI);
        PanGuBanner springbootBanner = new PanGuBanner(springbootResource);
        this.banners.add(springbootBanner);
        Resource panGuBootResource = resourceLoader.getResource(PanGuStarterConst.PAN_GU_BANNER_URI);
        PanGuBanner panGuBootBanner = new PanGuBanner(panGuBootResource);
        this.banners.add(panGuBootBanner);
    }

    public void addIfNotNull(Banner banner) {
        if (banner != null) {
            this.banners.add(banner);
        }
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (Banner banner : this.banners) {
            banner.printBanner(environment, sourceClass, out);
        }
    }

    public static final class PanGuBanner extends ResourceBanner {
        public PanGuBanner(Resource resource) {
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
            panGuBannerEnv.put("pangu-boot.version", getVersionString(PanGuStarterConst.PAN_GU_STARTER_VERSION, false));
            panGuBannerEnv.put("pangu-boot.formatted-version", getVersionString(PanGuStarterConst.PAN_GU_STARTER_VERSION, true));

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
}
