package com.yuebaix.pangu.autoconfigure;

import com.yuebaix.pangu.support.PanGuCompositeBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class PanGuEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private boolean bannerSettled = false;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (!bannerSettled) {
            PanGuCompositeBanner panGuCompositeBanner = new PanGuCompositeBanner();
            application.setBanner(panGuCompositeBanner);
            bannerSettled = true;
        }
    }
}
