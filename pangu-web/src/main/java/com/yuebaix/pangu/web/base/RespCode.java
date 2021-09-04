package com.yuebaix.pangu.web.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础回复编码
 */
@Data
@Accessors(chain = true)
public final class RespCode {
    /**回复码*/
    private Integer code;
    /**回复信息*/
    private String msg;

    RespCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
