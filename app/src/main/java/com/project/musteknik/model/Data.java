package com.project.musteknik.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("dataPlant")
	private List<DataPlantItem> dataPlant;

	@SerializedName("dataDepartemen")
	private List<DataDepartemenItem> dataDepartemen;

	@SerializedName("dataMesin")
	private List<DataMesinItem> dataMesin;

	@SerializedName("shift")
	private List<ShiftItem> shift;

	@SerializedName("dataStatus")
	private List<DataStatusItem> dataStatus;

	public List<DataPlantItem> getDataPlant(){
		return dataPlant;
	}

	public List<DataDepartemenItem> getDataDepartemen(){
		return dataDepartemen;
	}

	public List<DataMesinItem> getDataMesin(){
		return dataMesin;
	}

	public List<ShiftItem> getShift(){
		return shift;
	}

	public List<DataStatusItem> getDataStatus(){
		return dataStatus;
	}
}