package com.yuebaix.pangu.web.base;

import com.yuebaix.pangu.web.PanGuWebConst;
import com.yuebaix.pangu.web.PanGuWebException;

import java.util.HashMap;
import java.util.Map;

public final class RespCodeConst {
    private static final Map<Integer, RespCode> respCodeRegistry = new HashMap<>();

    public static final RespCode SUCCESS = validateAndGet(0, PanGuWebConst.SUCCESS);
    public static final RespCode FAIL = validateAndGet(1, PanGuWebConst.FAIL);
    public static final RespCode ERROR = validateAndGet(-100, PanGuWebConst.ERROR);
    public static final RespCode REVIEW = validateAndGet(-200, PanGuWebConst.REVIEW);

    public static RespCode validateAndGet(int code, String msg) {
        if (respCodeRegistry.get(code) != null) {
            throw new PanGuWebException("RespCode Already Exist");
        }
        RespCode respCode = new RespCode(code, msg);
        respCodeRegistry.put(code, respCode);
        return respCode;
    }

    /**
     * 用回复码返回对应枚举类型
     */
    public static RespCode getRespCode(int code) {
        for (Map.Entry<Integer, RespCode> entry : respCodeRegistry.entrySet()) {
            if (entry.getKey().equals(code)) {
                return  entry.getValue();
            }
        }
        return null;
    }
}
