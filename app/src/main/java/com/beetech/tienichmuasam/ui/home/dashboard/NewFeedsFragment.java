package com.beetech.tienichmuasam.ui.home.dashboard;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.viewpager.NewsFeedSlideAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.custom.behavior.CarouselEffectTransformer;
import com.beetech.tienichmuasam.databinding.FragmentNewFeedsBinding;
import com.beetech.tienichmuasam.utils.UIUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFeedsFragment extends BaseFragment<FragmentNewFeedsBinding> {
    private NewFeedsViewModel newFeedsViewModel;
    private NewsFeedSlideAdapter newsFeedSlideAdapter;
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

    }

    @Override
    public void initData() {
        newFeedsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewFeedsViewModel.class);

        newFeedsViewModel.getBanner().observe(getViewLifecycleOwner(),bannerResponseListResponse -> {
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

    }

}
