package com.beetech.tienichmuasam.ui.home.search;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.base.ListResponseBody;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.network.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends BaseViewModel {
    private MutableLiveData<ListLoadmoreReponse<ListProductResponse>> search = new MutableLiveData<>();
    private Repository repository;
    private String keySearch;
    private int pageIndex = 0;

    @Inject
    public SearchViewModel() {
    }


    public MutableLiveData<ListLoadmoreReponse<ListProductResponse>> getSearch() {
        return search;
    }

    public void search(String keyword,boolean isRefresh) {
        Type userListType = new TypeToken<ArrayList<ListProductResponse>>(){}.getType();
        List<ListProductResponse> data = new Gson().fromJson(producJs,userListType);
        search.setValue(new ListLoadmoreReponse<ListProductResponse>().success(new ListResponseBody<>(data), isRefresh,
                false));
//        mDisposable.add(
//                repository.getListProduct(categoryID, pageIndex)
//                        .doOnSubscribe(disposable -> {
//                            if (isRefresh) {
//                                listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().loading());
//                            }
//                        })
//                        .subscribe(
//                                response -> {
//                                    pageIndex++;
//                                    listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().success(response.getData(), isRefresh,
//                                            pageIndex <= response.getTotalPage()));
//                                },
//                                throwable -> {
//                                    listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().error(throwable));
//                                }
//                        )
//        );
    }

    private String producJs = " [{\n" +
            "        \"productId\": \"A101\",\n" +
            "        \"name\": \"Giày sneaker BoxandCox Deuce\",\n" +
            "        \"price\": 1670000,\n" +
            "        \"priceStr\": \"1.670.000 đ\",\n" +
            "        \"priceDiscount\": 790000,\n" +
            "        \"priceDiscountStr\": \"790.000 đ\",\n" +
            "        \"stockNum\": 0,\n" +
            "        \"totalSellCount\": 0,\n" +
            "        \"totalBuyCount\": 0,\n" +
            "        \"discount\": 880000,\n" +
            "        \"views\": null,\n" +
            "        \"imagePath\":\"/img/product/B202/B202.de_vang/B202.de_vang.thumbnail.jpg\",\n" +
            "        \"like\": 3,\n" +
            "        \"view\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"productId\": \"A101\",\n" +
            "        \"name\": \"Giày sneaker BoxandCox Deuce\",\n" +
            "        \"price\": 1670000,\n" +
            "        \"priceStr\": \"1.670.000 đ\",\n" +
            "        \"priceDiscount\": 790000,\n" +
            "        \"priceDiscountStr\": \"790.000 đ\",\n" +
            "        \"stockNum\": 0,\n" +
            "        \"totalSellCount\": 0,\n" +
            "        \"totalBuyCount\": 0,\n" +
            "        \"discount\": 880000,\n" +
            "        \"views\": null,\n" +
            "        \"imagePath\":\"/img/product/B202/B202.de_vang/B202.de_vang.thumbnail.jpg\",\n" +
            "        \"like\": 3,\n" +
            "        \"view\": 3\n" +
            "      }]";
}
