package com.project.musteknik.model;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private DataLogin dataLogin;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public DataLogin getDataLogin() {
        return dataLogin;
    }

    public String getCode() {
        return code;
    }
}
