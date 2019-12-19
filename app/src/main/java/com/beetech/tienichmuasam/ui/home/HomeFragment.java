package com.beetech.tienichmuasam.ui.home;

import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.SearchAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.HomeFragmentBinding;
import com.beetech.tienichmuasam.entity.SearchResponse;

import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {

    private HomeViewModel mViewModel;
    private SearchAdapter searchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);


        searchAdapter = new SearchAdapter(getContext());
        binding.rcvSearch.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvSearch.setAdapter(searchAdapter);
        binding.rcvSearch.setOnLoadingMoreListener(() -> mViewModel.search(false));

        binding.rcvSearch.setOnRefreshListener(() -> mViewModel.search(true));
        binding.rcvSearch.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            SearchResponse searchResponse = searchAdapter.getItem(position,SearchResponse.class);
            Toast.makeText(getContext(), searchResponse.getName()+"  "+searchResponse.getPrice(), Toast.LENGTH_SHORT).show();
        });
        mViewModel.search(true);
        mViewModel.getSearch().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()));
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvSearch.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvSearch.refresh(data);
        } else {
            binding.rcvSearch.addItem(data);
        }
    }
}
