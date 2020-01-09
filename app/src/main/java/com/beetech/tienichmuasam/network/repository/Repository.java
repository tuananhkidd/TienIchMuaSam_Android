package com.beetech.tienichmuasam.network.repository;

import com.beetech.tienichmuasam.BaseApplication;
import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.di.RxPreference;
import com.beetech.tienichmuasam.di.RxPreferenceImpl;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.entity.response.SearchResponse;
import com.beetech.tienichmuasam.entity.response.UserResponse;
import com.beetech.tienichmuasam.network.ApiInterface;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;
    private final RxPreference rxPreference;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
        this.rxPreference = new RxPreferenceImpl(BaseApplication.getContext());
    }

    public Single<ListResponse<SearchResponse>> search(int pageIndex) {
        return apiInterface.search("h", pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ListResponse<ListProductResponse>> getListProduct(Integer categoryID, int pageIndex) {
        return apiInterface.getListProduct(categoryID, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isLogin() {
        return rxPreference.isLogin();
    }

    public UserResponse getUserInfo() {
        return rxPreference.getUserInfo();
    }

    public void setUserInfo(UserResponse userResponse) {
        rxPreference.setUserInfo(userResponse);
    }
}
