package com.yuebaix.pangu.ready.logbook;

import com.yuebaix.pangu.ready.PanGuReadyStarterConst;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.json.JsonBodyFilters;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * only works on all levels inside the json tree and with string values
 */
public class PanGuLogbookBodyFilter implements BodyFilter {
    @Override
    public String filter(@Nullable String contentType, String body) {
        final Set<String> properties = new HashSet<>(PanGuLogbookConfigHolder.getObfuscateBodyParams());
        return JsonBodyFilters.replaceJsonStringProperty(properties, PanGuReadyStarterConst.LOGBOOK_OBFUSCATE_CNT).filter(contentType, body);
    }
}
