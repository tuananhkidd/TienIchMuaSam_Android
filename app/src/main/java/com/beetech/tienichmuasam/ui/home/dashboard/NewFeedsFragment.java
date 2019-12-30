package com.beetech.tienichmuasam.ui.home.dashboard;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.CategoryHomeAdapter;
import com.beetech.tienichmuasam.adapter.viewpager.NewsFeedSlideAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.custom.behavior.CarouselEffectTransformer;
import com.beetech.tienichmuasam.databinding.FragmentNewFeedsBinding;
import com.beetech.tienichmuasam.ui.list_product.ListProductFragment;
import com.beetech.tienichmuasam.utils.Constant;
import com.beetech.tienichmuasam.utils.DeviceUtil;
import com.beetech.tienichmuasam.utils.UIUtil;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFeedsFragment extends BaseFragment<FragmentNewFeedsBinding> {
    private NewFeedsViewModel newFeedsViewModel;
    private NewsFeedSlideAdapter newsFeedSlideAdapter;
    private CategoryHomeAdapter categoryHomeAdapter;

    public NewFeedsFragment() {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_feeds;
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
        binding.tvTop.setSelected(true);
        if (getActivity() != null) {
            binding.container.setPadding(0, DeviceUtil.getStatusBarHeight(getActivity()), 0, 0);
        }
        binding.btnTotal.setNumber(6,true);
    }

    @Override
    public void initData() {
        newFeedsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewFeedsViewModel.class);

        newFeedsViewModel.getBanner().observe(getViewLifecycleOwner(), bannerResponseListResponse -> {
            newsFeedSlideAdapter = new NewsFeedSlideAdapter(getActivity(), bannerResponseListResponse);
            binding.vpHotNews.setClipChildren(false);
            binding.vpHotNews.setClipToPadding(false);
            binding.vpHotNews.setPageMargin((int) UIUtil.convertDpToPixel(8, getActivity()));
            binding.vpHotNews.setOffscreenPageLimit(3);
            binding.vpHotNews.setPageTransformer(false, new CarouselEffectTransformer(getActivity())); // Set transformer
            binding.vpHotNews.setAdapter(newsFeedSlideAdapter);
            binding.indicator.setViewPager(binding.vpHotNews);
        });

        newFeedsViewModel.getListBanner();

        initHomeCategoryAdapter();

    }

    private void initHomeCategoryAdapter() {
        categoryHomeAdapter = new CategoryHomeAdapter();
        binding.rvItems.setAdapter(categoryHomeAdapter);

        newFeedsViewModel.getCategory().observe(getViewLifecycleOwner(), categoryResponses -> {
            categoryHomeAdapter.setData(categoryResponses);
            categoryHomeAdapter.setListener(position -> {
                HashMap<String,Integer> data = new HashMap<>();
                data.put(Constant.CATEGORY_ID,categoryResponses.get(position).getId());
                getViewController().addFragment(ListProductFragment.class,data);
            });
        });
        newFeedsViewModel.getListCategory();
    }
}
