package com.project.musteknik.model.masterdata;

import com.google.gson.annotations.SerializedName;

public class AnggotaTekniksItem{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("name")
	private String name;

	@SerializedName("nomor_handphone")
	private String nomorHandphone;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public int getPlantId(){
		return plantId;
	}

	public int getStatusId(){
		return statusId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getUserId(){
		return userId;
	}

	public String getJabatan(){
		return jabatan;
	}

	public Object getWhenDeleted(){
		return whenDeleted;
	}

	public String getName(){
		return name;
	}

	public String getNomorHandphone(){
		return nomorHandphone;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}