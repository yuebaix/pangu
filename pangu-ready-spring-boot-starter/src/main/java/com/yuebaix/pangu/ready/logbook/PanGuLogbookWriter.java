package com.yuebaix.pangu.ready.logbook;

import com.yuebaix.pangu.common.PanGuLog;
import lombok.extern.slf4j.Slf4j;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

import java.io.IOException;

@Slf4j
public class PanGuLogbookWriter implements HttpLogWriter {
    @Override
    public void write(Precorrelation precorrelation, String request) throws IOException {
        PanGuLog.info(log, request);
    }

    @Override
    public void write(Correlation correlation, String response) throws IOException {
        PanGuLog.info(log, response);
    }
}