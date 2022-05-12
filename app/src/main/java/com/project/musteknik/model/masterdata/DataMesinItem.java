package com.project.musteknik.model.masterdata;

import com.google.gson.annotations.SerializedName;

public class DataMesinItem{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("kode_mesin")
	private Object kodeMesin;

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("proses_id")
	private int prosesId;

	@SerializedName("id")
	private int id;

	@SerializedName("line_id")
	private int lineId;

	@SerializedName("terakhir_rusak")
	private String terakhirRusak;

	public int getPlantId(){
		return plantId;
	}

	public Object getKodeMesin(){
		return kodeMesin;
	}

	public int getStatusId(){
		return statusId;
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

	public int getProsesId(){
		return prosesId;
	}

	public int getId(){
		return id;
	}

	public int getLineId(){
		return lineId;
	}

	public String getTerakhirRusak(){
		return terakhirRusak;
	}
}