package com.project.musteknik.model.timeline;

import com.google.gson.annotations.SerializedName;

public class TeknikItem{

	@SerializedName("name")
	private String name = null;

	@SerializedName("id")
	private String id = null;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}