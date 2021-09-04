package com.yuebaix.pangu.core;

import com.yuebaix.pangu.common.PanGuFrameException;

public class PanGuCoreException extends PanGuFrameException {
    public PanGuCoreException(String message) {
        super(message);
    }
    public PanGuCoreException(Throwable cause) {
        super(cause);
    }
}
