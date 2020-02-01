package com.beetech.tienichmuasam.ui.home;

import android.view.WindowManager;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.viewpager.HomePagerAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.HomeFragmentBinding;
import com.beetech.tienichmuasam.utils.DialogUtil;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {

    private HomeViewModel mViewModel;
    private HomePagerAdapter homePagerAdapter;
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
        DialogUtil.getInstance(getContext()).hidden();
    }

    @Override
    public void initData() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        binding.vpHome.setAdapter(homePagerAdapter);
        binding.vpHome.setOffscreenPageLimit(5);
        binding.vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.bottomBar.setActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        binding.bottomBar.setOnItemSelectedListener(i -> binding.vpHome.setCurrentItem(i));

    }


}
