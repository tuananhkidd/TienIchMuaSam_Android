package com.beetech.tienichmuasam.ui.home.dashboard;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.entity.response.BannerResponse;
import com.beetech.tienichmuasam.entity.response.CategoryResponse;
import com.beetech.tienichmuasam.network.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class NewFeedsViewModel extends BaseViewModel {
    private MutableLiveData<ListResponse<BannerResponse>> banner = new MutableLiveData<>();
    private MutableLiveData<List<CategoryResponse>> category = new MutableLiveData<>();
    private Repository repository;

    public MutableLiveData<ListResponse<BannerResponse>> getBanner() {
        return banner;
    }

    public MutableLiveData<List<CategoryResponse>> getCategory() {
        return category;
    }

    @Inject
    public NewFeedsViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getListBanner() {
//        List<BannerResponse> data = new ArrayList<>();
//        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/1574734813319banner-web5.jpg"));
//        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/banner_2.png"));
//        data.add(new BannerResponse("https://tienichmuasam.com/img/banner/1576213325852banner-web%20(1).png"));
////        ListResponse<ListResponse<BannerResponse>> list = new ListResponse<>();
////        list.setData(data);
        mDisposable.add(repository.getListHomeBanner()
                .doOnSubscribe(disposable -> {
                    banner.setValue(new ListResponse<BannerResponse>().loading());
                })
                .subscribe(
                        bannerResponseListResponse -> {
                            banner.setValue(new ListResponse<BannerResponse>().success(bannerResponseListResponse.getData()));
                        }
                        , throwable -> {
                            banner.setValue(new ListResponse<BannerResponse>().error(throwable));
                        }
                ));
    }

    public void getListCategory() {
        List<CategoryResponse> data = new ArrayList<>();
        data.add(new CategoryResponse(null, R.drawable.ic_gerenals_home, "Tổng hợp"));
        data.add(new CategoryResponse(1, R.drawable.ic_shoe_home, "Giày"));
        data.add(new CategoryResponse(2, R.drawable.ic_sandal_home, "Dép"));
        data.add(new CategoryResponse(3, R.drawable.ic_clothe_home, "Áo"));
        data.add(new CategoryResponse(4, R.drawable.ic_wine_home, "Rượu vang"));
        data.add(new CategoryResponse(5, R.drawable.ic_bikini, "Đồ lót"));
        data.add(new CategoryResponse(6, R.drawable.ic_food_home, "Thực phẩm"));
        data.add(new CategoryResponse(7, R.drawable.ic_feedback, "Góp ý"));
        data.add(new CategoryResponse(8, R.drawable.ic_feedback, "Góp ý"));

        category.setValue(data);
    }
}
