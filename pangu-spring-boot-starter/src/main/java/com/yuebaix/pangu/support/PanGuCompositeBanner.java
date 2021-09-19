package com.yuebaix.pangu.support;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PanGuCompositeBanner implements Banner {
    private final List<Banner> banners = new ArrayList<>();

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
}
