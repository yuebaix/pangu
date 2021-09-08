package com.yuebaix.pangu.web.base;

import com.yuebaix.pangu.web.PanGuWebException;

public class PreCheckException extends PanGuWebException {
    private RespCode respCode = null;

    public RespCode getRespCode() {
        return this.respCode;
    }

    public PreCheckException(String errorCode) {
        super(errorCode);
    }

    public PreCheckException(RespCode respCode) {
        super(respCode.getMsg());
        this.respCode = respCode;
    }
}
