package com.project.musteknik.model.create;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("departemen_id")
	private int departemenId;

	@SerializedName("mesin_id")
	private int mesinId;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("status")
	private int status;

	public int getPlantId(){
		return plantId;
	}

	public String getNama(){
		return nama;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getDepartemenId(){
		return departemenId;
	}

	public int getMesinId(){
		return mesinId;
	}

	public Object getWhenDeleted(){
		return whenDeleted;
	}

	public String getNoWa(){
		return noWa;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public int getStatus(){
		return status;
	}
}