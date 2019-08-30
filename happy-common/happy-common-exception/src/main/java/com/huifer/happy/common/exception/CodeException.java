package com.huifer.happy.common.exception;

/**
 * 编码异常
 */
public class CodeException extends BaseException {
	public CodeException() {
		super();
	}

	public CodeException(int errorCode) {
		super(errorCode);
	}

	public CodeException(String message, int errorCode) {
		super(message, errorCode);
	}

	public CodeException(String message, Throwable cause, int errorCode) {
		super(message, cause, errorCode);
	}

	public CodeException(Throwable cause, int errorCode) {
		super(cause, errorCode);
	}

	public CodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
		super(message, cause, enableSuppression, writableStackTrace, errorCode);
	}
}
