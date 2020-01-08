package com.beetech.tienichmuasam.ui.cart;

import androidx.lifecycle.ViewModelProviders;



import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.CartFragmentBinding;

public class CartFragment extends BaseFragment<CartFragmentBinding> {

    private CartViewModel mViewModel;


    @Override
    protected int getLayoutId() {
        return R.layout.cart_fragment;
    }

    @Override
    public void backFromAddFragment() {
        getViewController().backFromAddFragment(null);
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(CartViewModel.class);
    }

    @Override
    public void initData() {
    }

}
