package com.yuebaix.pangu.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.common.PanGuLog;
import com.yuebaix.pangu.common.util.JacksonUtil;
import com.yuebaix.pangu.core.PanGuCoreConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * desc: a brief configurable PatternTypeFilter based on @ComponentScan
 *
 * usage:
 *
 * # 1.setting
 * # active config profile
 * pangu.scan.active=all
 * # config detail.profile name as key, json as value.first exclude then include.
 * pangu.scan.config={"all":{"exclude":[".*\.modulePkg\.pkg\.className"], "include":[".*\.modulePkg\.pkg\..*"]}}
 *
 * # 2.code (better right above @SpringBootApplication)
 * @ComponentScan(
 *         useDefaultFilters = false,
 *         includeFilters = {
 *                 @ComponentScan.Filter(type = FilterType.CUSTOM, classes = PanGuScan.IncludeTypeFilter.class)
 *         },
 *         excludeFilters = {
 *                 @ComponentScan.Filter(type = FilterType.CUSTOM, classes = PanGuScan.ExcludeTypeFilter.class)
 *         }
 * )
 * @SpringBootApplication
 * public class App {
 *     public static void main(String[] args) {
 *         SpringApplication.run(App.class, args);
 *     }
 * }
 */
@Slf4j
public abstract class PanGuScan implements EnvironmentAware {
    private static final int CHOICE_EXCLUDE = 0;
    private static final int CHOICE_INCLUDE = 1;

    private String activeProfile;
    private PanGuScanTypeFilterPatternConfig activePatternConfig;

    @Override
    public void setEnvironment(Environment environment) {
        String active = environment.getProperty(PanGuStarterConst.PAN_GU_SCAN_ACTIVE);
        if (!StringUtils.isEmpty(active)) {
            String config = environment.getProperty(PanGuStarterConst.PAN_GU_SCAN_CONFIG);
            if (!StringUtils.isEmpty(active)) {
                Map<String, PanGuScanTypeFilterConfig> configProfiles = JacksonUtil.read(config, new TypeReference<Map<String, PanGuScanTypeFilterConfig>>(){});
                PanGuScanTypeFilterConfig activeConfig = configProfiles.get(active);
                if (activeConfig != null) {
                    PanGuScanTypeFilterPatternConfig activePatternConfig = fromConfig(activeConfig);
                    this.activeProfile = active;
                    this.activePatternConfig = activePatternConfig;
                    PanGuLog.debug(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGuScan " + this.getClass().getSimpleName() +
                            " Initialized | active:{} | config:{}", active, JacksonUtil.write(activeConfig));
                }
            }
        }
    }

    protected boolean matchInternal(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory, int choice) {
        String className = metadataReader.getClassMetadata().getClassName();
        if (CHOICE_EXCLUDE == choice) {
            for (Pattern pattern : activePatternConfig.getExcludePattern()) {
                if (isMatch(pattern, className)) {
                    PanGuLog.debug(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGuScan Exclude | active:{} | pattern:{} | className:{}",
                            activeProfile, pattern.pattern(), className);
                    return true;
                }
            }
        } else if (CHOICE_INCLUDE == choice) {
            for (Pattern pattern : activePatternConfig.getIncludePattern()) {
                if (isMatch(pattern, className)) {
                    PanGuLog.debug(log, PanGuCoreConst.PAN_GU_TRACE_PREFIX + "PanGuScan Include | active:{} | pattern:{} | className:{}",
                            activeProfile, pattern.pattern(), className);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMatch(Pattern pattern, CharSequence content) {
        if (content == null || pattern == null) {
            return false;
        }
        return pattern.matcher(content).matches();
    }

    private PanGuScanTypeFilterPatternConfig fromConfig(PanGuScanTypeFilterConfig config) {
        PanGuScanTypeFilterPatternConfig patternConfig = new PanGuScanTypeFilterPatternConfig();
        if (config != null) {
            List<String> exclude = config.getExclude();
            patternConfig.setExcludePattern(strToPattern(exclude));
            List<String> include = config.getInclude();
            patternConfig.setIncludePattern(strToPattern(include));
        }
        return patternConfig;
    }

    private List<Pattern> strToPattern(List<String> strList) {
        List<Pattern> patternList = Collections.emptyList();
        if (!CollectionUtils.isEmpty(strList)) {
            patternList = strList.stream().map(Pattern::compile).collect(Collectors.toList());
        }
        return patternList;
    }

    @Data
    private static final class PanGuScanTypeFilterConfig {
        private List<String> include;
        private List<String> exclude;
    }

    @Data
    private static final class PanGuScanTypeFilterPatternConfig {
        private List<Pattern> includePattern;
        private List<Pattern> excludePattern;
    }

    public static final class ExcludeTypeFilter extends PanGuScan implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            return matchInternal(metadataReader, metadataReaderFactory, CHOICE_EXCLUDE);
        }
    }

    public static final class IncludeTypeFilter extends PanGuScan implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            return matchInternal(metadataReader, metadataReaderFactory, CHOICE_INCLUDE);
        }
    }
}
