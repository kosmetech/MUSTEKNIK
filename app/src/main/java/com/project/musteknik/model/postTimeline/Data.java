package com.project.musteknik.model.postTimeline;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("fromUser")
	private String fromUser;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("id_workorder")
	private String idWorkorder;

	public int getPlantId(){
		return plantId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getFromUser(){
		return fromUser;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getIdWorkorder(){
		return idWorkorder;
	}
}