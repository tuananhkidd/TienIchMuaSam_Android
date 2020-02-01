package com.beetech.tienichmuasam.ui.introduce;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.viewpager.IntroducePagerAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.WelcomeFragmentBinding;
import com.beetech.tienichmuasam.ui.home.HomeFragment;
import com.beetech.tienichmuasam.utils.DialogUtil;

public class WelcomeFragment extends BaseFragment<WelcomeFragmentBinding> {

    private WelcomeViewModel mViewModel;
    private IntroducePagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.welcome_fragment;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel.class);
        adapter = new IntroducePagerAdapter(getActivity());
        binding.viewPager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.viewPager);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();

        binding.btnNext.setOnClickListener(view -> {
            if (adapter.getCount() - 1 == binding.viewPager.getCurrentItem()) {
                DialogUtil.getInstance(getContext()).show();
                new Handler().postDelayed(() -> {
                    getViewController().replaceFragment(HomeFragment.class, null);
                }, 500);
            } else {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
            }

        });

        binding.btnSkip.setOnClickListener(view -> {
            DialogUtil.getInstance(getContext()).show();
            getViewController().replaceFragment(HomeFragment.class, null);
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.v("ahihi", "position => " + position);
            }

            @Override
            public void onPageSelected(int position) {
                if (adapter != null) {
                    if (position != adapter.getCount() - 1) {
                        binding.btnNext.setText(getString(R.string.next));
                        binding.btnSkip.setVisibility(View.VISIBLE);
                        binding.btnSkip.animate()
                                .translationY(0)
                                .setDuration(500)
                                .start();
                    } else {
                        binding.btnNext.setText(getString(R.string.start));
                        binding.btnSkip.animate()
                                .translationY(binding.btnSkip.getHeight())
                                .setDuration(300)
                                .start();
                        new Handler().postDelayed(() -> {
                            binding.btnSkip.setVisibility(View.GONE);
                        }, 300);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
