package com.project.musteknik.model.postTimeline;

import com.google.gson.annotations.SerializedName;

public class ResponsePostTimeline{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("onDepartemen")
	private String onDepartemen;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public String getOnDepartemen(){
		return onDepartemen;
	}

	public String getMessage(){
		return message;
	}
}