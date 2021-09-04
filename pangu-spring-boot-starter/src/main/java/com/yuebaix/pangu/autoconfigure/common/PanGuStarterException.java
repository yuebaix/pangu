package com.yuebaix.pangu.autoconfigure.common;

import com.yuebaix.pangu.common.PanGuFrameException;

public class PanGuStarterException extends PanGuFrameException {
    public PanGuStarterException(String message) {
        super(message);
    }
    public PanGuStarterException(Throwable cause) {
        super(cause);
    }
}
