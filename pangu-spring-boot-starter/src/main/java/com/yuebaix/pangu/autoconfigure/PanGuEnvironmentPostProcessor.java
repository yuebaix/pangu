package com.yuebaix.pangu.autoconfigure;

import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.support.PanGuCompositeBanner;
import com.yuebaix.pangu.support.PanGuResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PanGuEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private boolean bannerSettled = false;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (!bannerSettled) {
            if (PanGuStarterConst.PROPERTY_TRUE.equals(
                    environment.getProperty(PanGuStarterConst.PAN_GU_STARTER_BANNER_ENABLED, PanGuStarterConst.PROPERTY_TRUE))) {
                PanGuCompositeBanner panGuCompositeBanner = new PanGuCompositeBanner();
                ResourceLoader resourceLoader = new DefaultResourceLoader();
                if (PanGuStarterConst.PROPERTY_TRUE.equals(
                        environment.getProperty(PanGuStarterConst.PAN_GU_STARTER_BANNER_SPRINGBOOT_ENABLED, PanGuStarterConst.PROPERTY_FALSE))) {
                    Resource springbootResource = resourceLoader.getResource(PanGuStarterConst.SPRING_BOOT_BANNER_URI);
                    PanGuResourceBanner springbootResourceBanner = new PanGuResourceBanner(springbootResource);
                    panGuCompositeBanner.addIfNotNull(springbootResourceBanner);
                }
                if (PanGuStarterConst.PROPERTY_TRUE.equals(
                        environment.getProperty(PanGuStarterConst.PAN_GU_STARTER_BANNER_PAN_GU_ENABLED, PanGuStarterConst.PROPERTY_TRUE))) {
                    Resource panGuBootResource = resourceLoader.getResource(PanGuStarterConst.PAN_GU_BANNER_URI);
                    PanGuResourceBanner panGuBootResourceBanner = new PanGuResourceBanner(panGuBootResource);
                    panGuCompositeBanner.addIfNotNull(panGuBootResourceBanner);
                }
                application.setBanner(panGuCompositeBanner);
                bannerSettled = true;
            }
        }
    }
}
