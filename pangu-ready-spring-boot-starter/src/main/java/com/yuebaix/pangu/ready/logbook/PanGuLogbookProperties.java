package com.yuebaix.pangu.ready.logbook;

import com.yuebaix.pangu.ready.PanGuReadyStarterConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.List;

@ConfigurationProperties(prefix = PanGuReadyStarterConst.PAN_GU_READY_STARTER_LOGBOOK_PREFIX)
@Data
public class PanGuLogbookProperties {
    private List<String> obfuscateBodyParams;

    @PostConstruct
    public void init() {
        PanGuLogbookConfigHolder.set(this);
    }
}
