package com.project.musteknik.model.timeline;

import androidx.annotation.Nullable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Choice{

	@SerializedName("teknik")
	@Nullable
	private List<TeknikItem> teknik;

	@SerializedName("produksi")
	@Nullable
	private List<ProduksiItem> produksi;

	@Nullable
	public List<TeknikItem> getTeknik(){
		return teknik;
	}

	@Nullable
	public List<ProduksiItem> getProduksi(){
		return produksi;
	}
}