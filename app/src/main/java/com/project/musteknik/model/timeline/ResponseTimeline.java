package com.project.musteknik.model.timeline;

import com.google.gson.annotations.SerializedName;

public class ResponseTimeline{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("choice")
	private Choice choice;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public Choice getChoice(){
		return choice;
	}

	public String getMessage(){
		return message;
	}
}