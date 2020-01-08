package com.beetech.tienichmuasam.ui.profile;

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
import com.beetech.tienichmuasam.databinding.PersonFragmentBinding;

public class PersonFragment extends BaseFragment<PersonFragmentBinding> {

    private PersonViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.person_fragment;
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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(PersonViewModel.class);
    }

    @Override public void initData() {
        //fixme
        binding.tvName.setText(R.string.login_logout);
    }
}
