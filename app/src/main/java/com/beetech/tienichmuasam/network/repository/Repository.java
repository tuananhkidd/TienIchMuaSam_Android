package com.beetech.tienichmuasam.network.repository;

import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.entity.SearchResponse;
import com.beetech.tienichmuasam.network.ApiInterface;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<ListResponse<SearchResponse>> search(int pageIndex) {
        return apiInterface.search("h",pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
