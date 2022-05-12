package com.project.musteknik.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataLogin{

	@SerializedName("plant_id")
	private int plantId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("departemen_id")
	private String departemenId;

	@SerializedName("level")
	private int level;

	@SerializedName("whenDeleted")
	private Object whenDeleted;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("personal")
	private List<PersonalItem> personal;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	public int getPlantId(){
		return plantId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getDepartemenId() {
		return departemenId;
	}

	public int getLevel(){
		return level;
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

	public List<PersonalItem> getPersonal(){
		return personal;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}