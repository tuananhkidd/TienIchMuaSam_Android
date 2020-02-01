package com.beetech.tienichmuasam.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.beetech.tienichmuasam.entity.response.UserResponse;
import com.beetech.tienichmuasam.utils.Define;
import com.google.gson.Gson;

import javax.inject.Inject;

public class RxPreferenceImpl implements RxPreference {
    private final SharedPreferences mPrefs;
    private final SharedPreferences mFirstTimePrefs;

    private static final String PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO";
    private static final String PREF_IS_FIRST_TIME = "PREF_IS_FIRST_TIME";

    @Inject
    public RxPreferenceImpl(Context context) {
        mPrefs = context.getSharedPreferences(Define.PREF_FILE_NAME, Context.MODE_PRIVATE);
        mFirstTimePrefs = context.getSharedPreferences(Define.FIRST_TIME_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setUserInfo(UserResponse userInfo) {
        Gson gson = new Gson();
        if (userInfo == null) {
            mPrefs.edit().putString(PREF_KEY_USER_INFO, "").apply();
        } else {
            mPrefs.edit().putString(PREF_KEY_USER_INFO, gson.toJson(userInfo)).apply();
        }
    }

    @Override
    public boolean isLogin() {
        String userJs = mPrefs.getString(PREF_KEY_USER_INFO, "");
        if (TextUtils.isEmpty(userJs)) {
            return false;
        }
        return true;
    }

    @Override
    public UserResponse getUserInfo() {
        String userJs = mPrefs.getString(PREF_KEY_USER_INFO, "");
        if (TextUtils.isEmpty(userJs)) {
            return null;
        }
        return new Gson().fromJson(userJs, UserResponse.class);
    }

    @Override
    public boolean isFirstTime() {
        return mFirstTimePrefs.getBoolean(PREF_IS_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean isFirstTime) {
        mFirstTimePrefs.edit().putBoolean(PREF_IS_FIRST_TIME, isFirstTime).apply();
    }


}
