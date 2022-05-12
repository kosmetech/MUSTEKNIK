package com.project.musteknik.model.timeline;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("header_tiket")
	private HeaderTiket headerTiket;

	@SerializedName("timelines")
	private List<TimelinesItem> timelines;

	public HeaderTiket getHeaderTiket(){
		return headerTiket;
	}

	public List<TimelinesItem> getTimelines(){
		return timelines;
	}
}