package com.project.musteknik.model.timeline;

import com.google.gson.annotations.SerializedName;

public class HeaderTiket{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("status_name")
	private String statusName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("kode_mesin")
	private String kodeMesin;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("mesin_id")
	private int mesinId;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("mesin_name")
	private String mesinName;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("pelapor_id")
	private int pelaporId;

	@SerializedName("status")
	private int status;

	public int getPlantId(){
		return plantId;
	}

	public String getStatusName(){
		return statusName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getKodeMesin(){
		return kodeMesin;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getMesinId(){
		return mesinId;
	}

	public Object getWhenDeleted(){
		return whenDeleted;
	}

	public String getMesinName(){
		return mesinName;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public int getPelaporId(){
		return pelaporId;
	}

	public int getStatus(){
		return status;
	}
}