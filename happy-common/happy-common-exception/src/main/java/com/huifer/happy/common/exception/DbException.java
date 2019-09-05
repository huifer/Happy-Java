package com.huifer.happy.common.exception;

/**
 * 数据库异常
 */
public class DbException extends BaseException {
    public DbException() {
        super();
    }

    public DbException(int errorCode) {
        super(errorCode);
    }

    public DbException(String message, int errorCode) {
        super(message, errorCode);
    }

    public DbException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public DbException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public DbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
