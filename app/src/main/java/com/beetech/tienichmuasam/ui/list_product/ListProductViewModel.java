package com.beetech.tienichmuasam.ui.list_product;


import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.entity.ListProductResponse;
import com.beetech.tienichmuasam.network.repository.Repository;

import javax.inject.Inject;

public class ListProductViewModel extends BaseViewModel {
    private MutableLiveData<ListLoadmoreReponse<ListProductResponse>> listProducts = new MutableLiveData<>();
    private Repository repository;
    private Integer categoryID;
    private int pageIndex = 0;

    public MutableLiveData<ListLoadmoreReponse<ListProductResponse>> getListProducts() {
        return listProducts;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    @Inject
    public ListProductViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getListProduct(boolean isRefresh) {
        mDisposable.add(
                repository.getListProduct(categoryID, pageIndex)
                        .doOnSubscribe(disposable -> {
                            if (isRefresh) {
                                listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().loading());
                            }
                        })
                        .subscribe(
                                response -> {
                                    pageIndex++;
                                    listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().success(
                                            response.getData(), isRefresh,
                                            pageIndex <= response.getTotalPage()));
                                },
                                throwable -> {
                                    listProducts.setValue(new ListLoadmoreReponse<ListProductResponse>().error(throwable));
                                }
                        )
        );
    }
}
