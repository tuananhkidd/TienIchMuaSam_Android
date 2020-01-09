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

    private static final String PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO";

    @Inject
    public RxPreferenceImpl(Context context) {
        mPrefs = context.getSharedPreferences(Define.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setUserInfo(UserResponse userInfo) {
        Gson gson = new Gson();
        if (userInfo == null) {
            mPrefs.edit().putString(PREF_KEY_USER_INFO, "").apply();
        }else {
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
}
