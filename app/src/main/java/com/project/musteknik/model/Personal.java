package com.project.musteknik.model;

import com.google.gson.annotations.SerializedName;

public class Personal {

    @SerializedName("id")
    private String id;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("jabatan")
    private String jabatan;

    @SerializedName("nomor_handphone")
    private String nomor;

    @SerializedName("plant_id")
    private String plant;

    @SerializedName("status_id")
    private String status;

    @SerializedName("whenDeleted")
    private String del = null;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;
}
