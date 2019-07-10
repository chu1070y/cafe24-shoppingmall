package com.cafe24.shoppingmall.dto;

public class JSONResult {
	private String result; // success, fail 둘 중 하나만 써라.
	private String message; // if fail, message 세팅해라.
	private Object data; // if success, data 넣어라
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	private JSONResult(String result, String message, Object data) {	
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
}
