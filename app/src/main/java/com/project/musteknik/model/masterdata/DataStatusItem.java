package com.project.musteknik.model.masterdata;

import com.google.gson.annotations.SerializedName;

public class DataStatusItem{

	@SerializedName("plant_id")
	private int plantId;

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

	@SerializedName("from_table")
	private String fromTable;

	public int getPlantId(){
		return plantId;
	}

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

	public String getFromTable(){
		return fromTable;
	}
}