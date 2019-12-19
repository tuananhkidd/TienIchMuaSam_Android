package com.beetech.tienichmuasam.ui.home.dashboard;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.entity.BannerResponse;

import java.util.ArrayList;
import java.util.List;

public class NewFeedsViewModel extends BaseViewModel {
    private MutableLiveData<List<BannerResponse>> banner = new MutableLiveData<>();

    public MutableLiveData<List<BannerResponse>> getBanner() {
        return banner;
    }

    public void getListBanner(){
        List<BannerResponse> data = new ArrayList<>();
        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/1574734813319banner-web5.jpg"));
        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/banner_2.png"));
        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/1576213325852banner-web%20(1).png"));
//        ListResponse<ListResponse<BannerResponse>> list = new ListResponse<>();
//        list.setData(data);
        banner.setValue(data);
    }
}
