package com.project.musteknik.model.masterdata;

import com.google.gson.annotations.SerializedName;

public class DataDepartemenItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getWhenDeleted(){
		return whenDeleted;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}