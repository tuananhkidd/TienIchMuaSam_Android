package com.beetech.tienichmuasam.ui.profile;


import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.entity.response.UserResponse;
import com.beetech.tienichmuasam.network.repository.Repository;

import javax.inject.Inject;

public class PersonViewModel extends BaseViewModel {

    private Repository repository;

    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();

    private MutableLiveData<UserResponse> user = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    public MutableLiveData<UserResponse> getUser() {
        return user;
    }

    @Inject
    public PersonViewModel(Repository repository) {
        this.repository = repository;

        setData();
    }


    public void setUserInfo() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId("UI10001");
        userResponse.setUserName("Tuấn Anh Kidd");

        repository.setUserInfo(userResponse);
        setData();
    }

    private void setData(){
        isLogin.setValue(repository.isLogin());
        user.setValue(repository.getUserInfo());
    }

    public void onClickLogout(){
        repository.setUserInfo(null);
        setData();
    }

}
