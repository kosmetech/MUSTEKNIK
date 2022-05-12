package com.project.musteknik.model.masterdata;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("sparepart")
	private List<SparepartItem> sparepart;

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

	@SerializedName("anggota_tekniks")
	private List<AnggotaTekniksItem> anggotaTekniks;

	public List<SparepartItem> getSparepart(){
		return sparepart;
	}

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

	public List<AnggotaTekniksItem> getAnggotaTekniks(){
		return anggotaTekniks;
	}
}