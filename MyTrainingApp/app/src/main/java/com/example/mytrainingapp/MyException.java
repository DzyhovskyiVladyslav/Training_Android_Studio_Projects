package com.example.mytrainingapp;

import android.util.Log;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class MyException extends RuntimeException{

	private static final String DOT = ". ";
	private static final String RESPONSE_NAME = "responseName=";
	private static final String VALIDATION = "Validation";
	private  final String failureName;
	private  final String failureMessage;

	private MyException(String failureName, String failureMessage, Throwable cause) {
		super(cause);
		this.failureName = failureName;
		this.failureMessage = failureMessage;
		Log.e(failureName, getMessage(), cause);
	}

	private MyException(String failureName, String failureMessage){
		this(failureName, failureMessage, null);
	}

	public static MyException error(Exception e) {
		return new MyException(e.getClass().getName(), e.getMessage(), e);
	}

	public static MyException validation( String message) {
		LoginFormVM.setError(message);
		return new MyException(VALIDATION, message);
	}

	@Override
	public String getMessage() {
		return RESPONSE_NAME + failureName + DOT + failureMessage;
	}
}
