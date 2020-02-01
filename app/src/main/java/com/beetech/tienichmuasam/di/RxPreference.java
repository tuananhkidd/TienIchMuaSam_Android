package com.beetech.tienichmuasam.di;

import com.beetech.tienichmuasam.entity.response.UserResponse;

import javax.inject.Singleton;

@Singleton
public interface RxPreference {
    void setUserInfo(UserResponse userInfo);

    boolean isLogin();

    boolean isFirstTime();

    UserResponse getUserInfo();

    public void setFirstTime(boolean isFirstTime);
}
