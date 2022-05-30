package com.project.musteknik.model.detail;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("nama_status")
	private String namaStatus;

	@SerializedName("nama_plant")
	private String namaPlant;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("pelapor_name")
	private String pelaporName;

	@SerializedName("pelapor_wa")
	private String pelaporWa;

	@SerializedName("pelapor_id")
	private int pelaporId;

	@SerializedName("nama_mesin")
	private String namaMesin;

	@SerializedName("kode_mesin")
	private String kodeMesin;

	public String getKodeMesin() {
		return kodeMesin;
	}

	public String getNamaStatus(){
		return namaStatus;
	}

	public String getNamaPlant(){
		return namaPlant;
	}

	public String getJabatan(){
		return jabatan;
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

	public String getPelaporName(){
		return pelaporName;
	}

	public String getPelaporWa(){
		return pelaporWa;
	}

	public int getPelaporId(){
		return pelaporId;
	}

	public String getNamaMesin(){
		return namaMesin;
	}
}