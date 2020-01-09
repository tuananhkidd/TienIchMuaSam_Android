package com.beetech.tienichmuasam.ui.profile;

import androidx.lifecycle.ViewModelProviders;

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
        //fixme test
        binding.layoutUserInfo.setOnClickListener(v->{
            mViewModel.setUserInfo();
        });

        binding.setUserVM(mViewModel);
    }
}
