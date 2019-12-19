package com.beetech.tienichmuasam.network.model;

import com.beetech.tienichmuasam.utils.Define;
import com.google.gson.annotations.SerializedName;

public class ApiObjectResponse<T> {
    @SerializedName(Define.Api.BaseResponse.SUCCESS)
    private Integer success;

    @SerializedName(Define.Api.BaseResponse.DATA)
    private T data;

    @SerializedName(Define.Api.BaseResponse.ERROR)
    private RequestError error;

    public Integer getSuccess() {
        return success;
    }

    public boolean isSuccess() {
        return success == 1;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RequestError getRequestError() {
        return error;
    }

    public void setRequestError(RequestError error) {
        this.error = error;
    }
}
