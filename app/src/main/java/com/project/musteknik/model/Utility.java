package com.project.musteknik.model;

import com.google.gson.annotations.SerializedName;

import okhttp3.internal.Util;

public class Utility {

    private String id;
    private String nama;

    public Utility(String id, String nama){
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
