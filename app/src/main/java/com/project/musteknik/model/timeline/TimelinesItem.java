package com.project.musteknik.model.timeline;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TimelinesItem{

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

	@SerializedName("pelapor")
	private List<String> pelapor;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("id_workorder")
	private int idWorkorder;

	@SerializedName("departemen")
	private String departemen;

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

	public List<String> getPelapor(){
		return pelapor;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public int getIdWorkorder(){
		return idWorkorder;
	}

	public String getDepartemen(){
		return departemen;
	}
}