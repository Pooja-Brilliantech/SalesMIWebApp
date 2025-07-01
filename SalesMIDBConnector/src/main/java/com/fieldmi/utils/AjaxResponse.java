package com.fieldmi.utils;

public class AjaxResponse<T> {
	private T data;
	private int status = 0;
	private String message = "";

	public AjaxResponse(T data) {
		this.data = data;
	}

	public AjaxResponse() {

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
