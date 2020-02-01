package com.beetech.tienichmuasam.ui.auth;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.LoginFragmentBinding;

public class LoginFragment extends BaseFragment<LoginFragmentBinding> {

    private LoginViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);

    }

    @Override
    public void initData() {

    }
}
