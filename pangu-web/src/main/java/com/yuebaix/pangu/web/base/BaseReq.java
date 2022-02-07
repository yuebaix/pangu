package com.yuebaix.pangu.web.base;

import lombok.Data;

/**
 * 基础请求类
 */
@Data
public class BaseReq<T> {
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
