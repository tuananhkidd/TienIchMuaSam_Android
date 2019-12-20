package com.beetech.tienichmuasam.ui.list_product;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ListProductAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.ListProductFragmentBinding;
import com.beetech.tienichmuasam.entity.ListProductResponse;
import com.beetech.tienichmuasam.utils.DeviceUtil;

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
        if (getActivity() != null) {
            binding.container.setPadding(0, DeviceUtil.getStatusBarHeight(getActivity()), 0, 0);
        }
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
        });
        mViewModel.getListProduct(true);
        mViewModel.getListProducts().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()));

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
