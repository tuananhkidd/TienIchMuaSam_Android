package com.beetech.tienichmuasam.ui.splash;

import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.SplashFragmentBinding;
import com.beetech.tienichmuasam.ui.home.HomeFragment;
import com.beetech.tienichmuasam.ui.introduce.WelcomeFragment;
import com.beetech.tienichmuasam.utils.DialogUtil;


public class SplashFragment extends BaseFragment<SplashFragmentBinding> {

    private SplashViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> {
            if (mViewModel.isFirstTime()) {
                mViewModel.setFirstTime(false);
                getViewController().replaceFragment(WelcomeFragment.class, null);
            } else {
                getViewController().replaceFragment(HomeFragment.class, null);
            }
        }, 2000);
    }

}
