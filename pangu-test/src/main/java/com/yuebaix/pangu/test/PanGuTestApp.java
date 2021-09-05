package com.yuebaix.pangu.test;

import com.yuebaix.pangu.support.PanGuScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = PanGuScan.IncludeTypeFilter.class)
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = PanGuScan.ExcludeTypeFilter.class)
        }
)
@SpringBootApplication
public class PanGuTestApp {
    public static void main(String[] args) {
        SpringApplication.run(PanGuTestApp.class, args);
    }
}
