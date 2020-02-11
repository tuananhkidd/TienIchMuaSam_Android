package com.beetech.tienichmuasam.base;

import androidx.annotation.NonNull;

import com.beetech.tienichmuasam.utils.Define;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class ListLoadmoreReponse<T> extends BaseResponse{
    private boolean isRefresh;
    private boolean canLoadmore;
    private int type;


    @Nullable
    private ListResponseBody<T> data;

    @Nullable
    private Throwable error;

    public ListLoadmoreReponse() {
    }

    public boolean isCanLoadmore() {
        return canLoadmore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public ListLoadmoreReponse(int status, ListResponseBody<T> data, Throwable error, boolean isRefresh, boolean canLoadmore) {
        this.isRefresh = isRefresh;
        this.canLoadmore = canLoadmore;
        this.type = status;
        this.data = data;
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public ListResponseBody<T> getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public ListLoadmoreReponse<T> success(@NonNull ListResponseBody<T> data, boolean isRefresh, boolean canLoadmore) {
        this.isRefresh = isRefresh;
        this.canLoadmore = canLoadmore;
        return new ListLoadmoreReponse<>(Define.ResponseStatus.SUCCESS, data, null, isRefresh, canLoadmore);
    }

    public ListLoadmoreReponse<T> loading() {
        return new ListLoadmoreReponse<>(Define.ResponseStatus.LOADING, null, null, false, false);
    }

    public ListLoadmoreReponse<T> error(@NonNull Throwable throwable) {
        return new ListLoadmoreReponse<>(Define.ResponseStatus.ERROR, null, throwable, false, false);
    }
}
