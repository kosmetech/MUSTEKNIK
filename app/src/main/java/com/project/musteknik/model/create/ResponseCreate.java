package com.project.musteknik.model.create;

import com.google.gson.annotations.SerializedName;

public class ResponseCreate{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}