package com.beetech.tienichmuasam.ui.product;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.BaseApplication;
import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ObjectResponse;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.utils.Utils;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DetaillProductViewModel extends BaseViewModel {
    private MutableLiveData<ObjectResponse<DetailProductResponse>> detail = new MutableLiveData<>();
    private String productId;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public MutableLiveData<ObjectResponse<DetailProductResponse>> getDetail() {
        return detail;
    }

    @Inject
    public DetaillProductViewModel() {

    }

    public void getDetailProduct(){
        //fixme call api
        DetailProductResponse detailProductResponse = new Gson().fromJson(Utils.readJsonFormAsset(BaseApplication.getContext(),"detail.json"),DetailProductResponse.class);
        detail.setValue(new ObjectResponse<DetailProductResponse>().success(detailProductResponse));
    }

}
