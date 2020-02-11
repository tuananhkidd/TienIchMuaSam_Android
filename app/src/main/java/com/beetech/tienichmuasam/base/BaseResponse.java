package com.beetech.tienichmuasam.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
