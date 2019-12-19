package com.beetech.tienichmuasam.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mDisposable;

    public BaseViewModel(){
        mDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
