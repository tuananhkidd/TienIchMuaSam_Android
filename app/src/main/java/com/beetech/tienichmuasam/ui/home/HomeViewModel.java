package com.beetech.tienichmuasam.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.entity.SearchResponse;
import com.beetech.tienichmuasam.network.repository.Repository;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;
    private MutableLiveData<ListLoadmoreReponse<SearchResponse>> search = new MutableLiveData<>();
    private int pageIndex =  1;

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ListLoadmoreReponse<SearchResponse>> getSearch() {
        return search;
    }

    public void search(boolean isRefresh) {
        if(isRefresh){
            pageIndex = 1;
        }
        mDisposable.add(
                repository.search(pageIndex)
                        .doOnSubscribe(disposable -> {
                            if (isRefresh) {
                                search.setValue(new ListLoadmoreReponse<SearchResponse>().loading());
                            }
                        })
                        .subscribe(
                                response -> {
                                    pageIndex++;
                                    search.setValue(new ListLoadmoreReponse<SearchResponse>().success(response.getData(), isRefresh,
                                            pageIndex <= response.getTotalPage()));
                                },
                                throwable -> {
                                    search.setValue(new ListLoadmoreReponse<SearchResponse>().error(throwable));
                                }
                        )
        );
    }
}
