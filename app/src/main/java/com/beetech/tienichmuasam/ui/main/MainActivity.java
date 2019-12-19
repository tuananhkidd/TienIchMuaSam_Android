package com.beetech.tienichmuasam.ui.main;

import android.os.Bundle;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseActivity;
import com.beetech.tienichmuasam.databinding.ActivityMainBinding;
import com.beetech.tienichmuasam.ui.splash.SplashFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.flMainContainer;
    }

    @Override
    public void initView() {
        mViewController.addFragment(SplashFragment.class, null);
    }

    @Override
    public void initData() {

    }
}
