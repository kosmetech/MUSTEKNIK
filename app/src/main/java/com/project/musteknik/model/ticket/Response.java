package com.project.musteknik.model.ticket;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}