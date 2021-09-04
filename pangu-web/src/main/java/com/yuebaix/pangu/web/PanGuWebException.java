package com.yuebaix.pangu.web;

import com.yuebaix.pangu.common.PanGuFrameException;

public class PanGuWebException extends PanGuFrameException {
    public PanGuWebException(String message) {
        super(message);
    }
    public PanGuWebException(Throwable cause) {
        super(cause);
    }
}