package com.beetech.tienichmuasam.ui.product;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.SimilarProductAdapter;
import com.beetech.tienichmuasam.adapter.viewpager.DetailSlideImagePagerAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.custom.behavior.RecyclerViewLinearSnapHelper;
import com.beetech.tienichmuasam.databinding.DetaillProductFragmentBinding;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.utils.Constant;
import com.beetech.tienichmuasam.utils.DeviceUtil;

public class DetaillProductFragment extends BaseFragment<DetaillProductFragmentBinding> {

    private DetaillProductViewModel mViewModel;
    private DetailSlideImagePagerAdapter detailSlideImagePagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.detaill_product_fragment;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetaillProductViewModel.class);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.toolbar.getLayoutParams();
        if (getActivity() != null) {
            layoutParams.topMargin = DeviceUtil.getStatusBarHeight(getActivity());
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            mViewModel.setProductId(bundle.getString(Constant.PRODUCT_ID));
            mViewModel.getDetailProduct();
        }
    }

    @Override
    public void initData() {
        mViewModel.getDetail().observe(getViewLifecycleOwner(), detailProductResponseObjectResponse -> {
            binding.setDetail(detailProductResponseObjectResponse.getData());
            loadData(detailProductResponseObjectResponse.getData());
        });
    }

    private void loadData(DetailProductResponse detailProductResponse) {
        if (detailProductResponse != null) {
            binding.wvInfo.loadDataWithBaseURL("https://tienichmuasam.com/", detailProductResponse.getDescription(), "text/html", "UTF-8", null);
            //region slide
            detailSlideImagePagerAdapter = new DetailSlideImagePagerAdapter(getContext(), detailProductResponse.getImages());
            binding.vpImage.setAdapter(detailSlideImagePagerAdapter);
            binding.tvPosition.setText("1/" + detailProductResponse.getImages().size() + "");
            binding.vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    binding.tvPosition.setText("" + (position + 1) + "/" + detailProductResponse.getImages().size() + "");
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            //endregion
            //region simiar product
            SimilarProductAdapter similarProductAdapter = new SimilarProductAdapter(getContext());
            similarProductAdapter.addModels(detailProductResponse.getSimilarProducts(), false);
            binding.rcvSimilarProduct.setAdapter(similarProductAdapter);
            RecyclerViewLinearSnapHelper snapHelper = new RecyclerViewLinearSnapHelper();
            snapHelper.attachToRecyclerView(binding.rcvSimilarProduct);
            //endregion
            //fixme check favourite product
        }
    }

}
