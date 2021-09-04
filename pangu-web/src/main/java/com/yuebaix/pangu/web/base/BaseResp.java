package com.yuebaix.pangu.web.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础回复类
 */
@ApiModel("回复基类")
@Data
public class BaseResp<T> {
    /**描述*/
    @ApiModelProperty(value = "描述", example = "成功")
    private String msg;
    /**编码*/
    @ApiModelProperty(value = "编码", example = "0")
    private Integer code;
    /**数据内容*/
    @ApiModelProperty("数据")
    private T data;

    /**
     * <p>description : 默认成功(不返回数据)</p>
     */
    public static BaseResp<?> success() {
        return BaseResp.discuss(RespCodeConst.SUCCESS, null);
    }

    /**
     * <p>description : 成功(返回数据)</p>
     */
    public static <T> BaseResp<T> success(T data) {
        return BaseResp.discuss(RespCodeConst.SUCCESS, data);
    }

    /**
     * <p>description : 默认失败(不返回数据)</p>
     */
    public static BaseResp<?> fail() {
        return BaseResp.discuss(RespCodeConst.FAIL, null);
    }

    /**
     * <p>description : 失败(返回信息)</p>
     */
    public static BaseResp<?> fail(String msg) {
        return BaseResp.custom(RespCodeConst.FAIL.getCode(), RespCodeConst.FAIL.getMsg() + ":" + msg, null);
    }

    /**
     * <p>description : 失败(返回数据)</p>
     */
    public static <T> BaseResp<T> fail(T data) {
        return BaseResp.discuss(RespCodeConst.FAIL, data);
    }

    /**
     * <p>description : 提示校验异常</p>
     */
    public static BaseResp<?> review() {
        return BaseResp.discuss(RespCodeConst.REVIEW, null);
    }

    /**
     * <p>description : 提示校验异常(返回信息)</p>
     */
    public static BaseResp<?> review(String msg) {
        return BaseResp.custom(RespCodeConst.REVIEW.getCode(), RespCodeConst.REVIEW.getMsg() + ":" + msg, null);
    }

    /**
     * <p>description : 提示校验异常(返回数据)</p>
     */
    public static <T> BaseResp<T> review(T data) {
        return BaseResp.discuss(RespCodeConst.REVIEW, data);
    }

    /**
     * <p>description : 错误</p>
     */
    public static BaseResp<?> error() {
        return BaseResp.discuss(RespCodeConst.ERROR, null);
    }

    /**
     * <p>description : 自定义返回码与数据(需要在BaseCode中建立新的枚举类)</p>
     */
    public static <T> BaseResp<T> custom(RespCode code, T data) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(code.getCode());
        resp.setMsg(code.getMsg());
        resp.setData(data);
        return resp;
    }

    /**
     * <p>description : 自定义返回码与数据</p>
     */
    public static <T> BaseResp<T> custom(Integer code, String msg, T data) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }

    private static <T> BaseResp<T> discuss(RespCode code, T data) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(code.getCode());
        resp.setMsg(code.getMsg());
        resp.setData(data);
        return resp;
    }
}
