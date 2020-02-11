package com.beetech.tienichmuasam.ui.list_product;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ListProductAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.ListProductFragmentBinding;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.ui.product.DetaillProductFragment;
import com.beetech.tienichmuasam.utils.Constant;

import java.util.HashMap;
import java.util.List;

import static com.beetech.tienichmuasam.utils.Constant.CATEGORY_ID;

public class ListProductFragment extends BaseFragment<ListProductFragmentBinding> {

    private ListProductViewModel mViewModel;
    private ListProductAdapter listProductAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.list_product_fragment;
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
        binding.toolbar.setLeftListener(() -> {
            getViewController().backFromAddFragment(null);
        });
    }

    @Override
    public void initData() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProductViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mViewModel.setCategoryID(bundle.getInt(CATEGORY_ID));
        }
        listProductAdapter = new ListProductAdapter(getContext());
        binding.rcvListProduct.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvListProduct.setAdapter(listProductAdapter);
        binding.rcvListProduct.setOnLoadingMoreListener(() -> mViewModel.getListProduct(false));

        binding.rcvListProduct.setOnRefreshListener(() -> mViewModel.getListProduct(true));
        binding.rcvListProduct.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            ListProductResponse searchResponse = listProductAdapter.getItem(position, ListProductResponse.class);
            HashMap<String, String> data = new HashMap<>();
            data.put(Constant.PRODUCT_ID, searchResponse.getProductId());
            data.put(Constant.COLOR_ID, searchResponse.getColorId());
            getViewController().addFragment(DetaillProductFragment.class, data);
        });
        mViewModel.getListProduct(true);
        mViewModel.getListProducts().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()));

        showLoading();

        new Handler().postDelayed(this::hideLoading, 5000);
    }


    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvListProduct.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvListProduct.refresh(data);
        } else {
            binding.rcvListProduct.addItem(data);
        }
    }

}
