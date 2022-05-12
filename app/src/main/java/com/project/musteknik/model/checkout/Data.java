package com.project.musteknik.model.checkout;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("fromUser")
	private int fromUser;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private Object deskripsi;

	@SerializedName("id_workorder")
	private int idWorkorder;

	public int getPlantId(){
		return plantId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getFromUser(){
		return fromUser;
	}

	public Object getWhenDeleted(){
		return whenDeleted;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public Object getDeskripsi(){
		return deskripsi;
	}

	public int getIdWorkorder(){
		return idWorkorder;
	}
}