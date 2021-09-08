package com.yuebaix.pangu.web.base;

public class PreCheck {
    private PreCheck() {}

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new PreCheckException(RespCodeConst.REVIEW);
        }
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new PreCheckException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new PreCheckException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkArgument(boolean expression, RespCode codeMsg) {
        if (!expression) {
            throw new PreCheckException(codeMsg);
        }
    }

    public static void checkArgument(boolean expression, int code, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new PreCheckException(new RespCode(code, String.format(errorMessageTemplate, errorMessageArgs)));
        }
    }

    public static void checkArgument(boolean expression, int code, String msg) {
        if (!expression) {
            throw new PreCheckException(new RespCode(code, msg));
        }
    }
}
