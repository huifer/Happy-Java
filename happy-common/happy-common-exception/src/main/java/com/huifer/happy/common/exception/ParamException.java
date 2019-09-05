package com.huifer.happy.common.exception;

/**
 * 参数异常
 */
public class ParamException extends BaseException {
    private static final long serialVersionUID = 1252304382825478094L;

    public ParamException() {
        super();
    }

    public ParamException(int errorCode) {
        super(errorCode);
    }

    public ParamException(String message, int errorCode) {
        super(message, errorCode);
    }

    public ParamException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public ParamException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}

