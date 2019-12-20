package com.beetech.tienichmuasam.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    private int status;
    private int msg;

    public int getStatus() {
        return status;
    }

    public int getMsg() {
        return msg;
    }
}
