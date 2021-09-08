package com.yuebaix.pangu.ready.logbook;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PanGuLogbookConfigHolder {
    private static Set<String> obfuscateBodyParams = Collections.emptySet();

    static void set(PanGuLogbookProperties delayValue) {
        if (delayValue != null && !CollectionUtils.isEmpty(delayValue.getObfuscateBodyParams())) {
            obfuscateBodyParams = new HashSet<>(delayValue.getObfuscateBodyParams());
        }
    }

    public static Set<String> getObfuscateBodyParams() {
        return obfuscateBodyParams;
    }
}
