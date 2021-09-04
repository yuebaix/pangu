package com.yuebaix.pangu.web.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础请求类
 */
@ApiModel("请求基类")
@Data
public class BaseReq<T> {
    @ApiModelProperty("内容")
    private T cnt;

    /**
     * <p>description : 构造请求类</p>
     */
    public static <T> BaseReq<T> of(T cnt) {
        BaseReq<T> req = new BaseReq<>();
        req.setCnt(cnt);
        return req;
    }
}
