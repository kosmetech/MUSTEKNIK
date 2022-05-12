package com.project.musteknik.model.masterdata;

import com.google.gson.annotations.SerializedName;

public class SparepartItem{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("kode_barang")
	private Object kodeBarang;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("stock")
	private int stock;

	@SerializedName("minimum")
	private int minimum;

	public int getPlantId(){
		return plantId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getSatuan(){
		return satuan;
	}

	public Object getKodeBarang(){
		return kodeBarang;
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

	public int getStock(){
		return stock;
	}

	public int getMinimum(){
		return minimum;
	}
}