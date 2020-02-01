package com.beetech.tienichmuasam.ui.splash;


import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.network.repository.Repository;

import javax.inject.Inject;

public class SplashViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    SplashViewModel(Repository repository) {
        this.repository = repository;
    }

    public boolean isFirstTime() {
        return repository.isFirstTime();
    }

    public void setFirstTime(boolean isFirstTime) {
        repository.setFirstTime(isFirstTime);
    }
}
