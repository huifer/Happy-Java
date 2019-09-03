package com.huifer.happy.common.exception;

/**
 * 验证异常
 */
public class CheckException extends  BaseException {
    public CheckException() {
        super();
    }

    public CheckException(int errorCode) {
        super(errorCode);
    }

    public CheckException(String message, int errorCode) {
        super(message, errorCode);
    }

    public CheckException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public CheckException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
