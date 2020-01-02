package com.beetech.tienichmuasam.ui.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.CommentAdapter;
import com.beetech.tienichmuasam.adapter.SimilarProductAdapter;
import com.beetech.tienichmuasam.adapter.viewpager.DetailSlideImagePagerAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.custom.behavior.RecyclerViewLinearSnapHelper;
import com.beetech.tienichmuasam.custom.dialog.SelectProductDialog;
import com.beetech.tienichmuasam.databinding.DetaillProductFragmentBinding;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.utils.Constant;
import com.beetech.tienichmuasam.utils.DeviceUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class DetaillProductFragment extends BaseFragment<DetaillProductFragmentBinding> implements EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {

    private DetaillProductViewModel mViewModel;
    private DetailSlideImagePagerAdapter detailSlideImagePagerAdapter;
    BottomSheetBehavior sheetBehavior;
    private CommentAdapter commentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.detaill_product_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        if (sheetBehavior != null) {
            if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                getViewController().backFromAddFragment(null);
            }
        }
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetaillProductViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mViewModel.setProductId(bundle.getString(Constant.PRODUCT_ID));
            mViewModel.getDetailProduct();
        }

        binding.btnBack.setOnClickListener(v -> {
            getViewController().backFromAddFragment(null);
        });
        initLayoutComment();
    }

    @Override
    public void initData() {
        mViewModel.getDetail().observe(getViewLifecycleOwner(), detailProductResponseObjectResponse -> {
            binding.setDetail(detailProductResponseObjectResponse.getData());
            loadData(detailProductResponseObjectResponse.getData());
        });

        binding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            //0->~400
            float percent =(float) scrollY / binding.vpImage.getHeight();
            Log.v("ahuhu","offset : "+percent);
            if (percent > 1) {
                percent = 1;
                binding.btnBack.setImageResource(R.drawable.ic_back);
                binding.btnShare.setImageResource(R.drawable.ic_share);
                binding.btnCart.setImageResource(R.drawable.ic_back);
                binding.btnCart.setColorFilter(ContextCompat.getColor(getContext(),R.color.white));
            }else {
                binding.btnBack.setImageResource(R.drawable.ic_back_bg);
                binding.btnShare.setImageResource(R.drawable.ic_share);
                binding.btnCart.setImageResource(R.drawable.ic_cart);
                binding.btnCart.setColorFilter(ContextCompat.getColor(getContext(),R.color.white));
            }
            binding.tvProduct.setAlpha(percent);
            binding.viewBackground.setAlpha(percent);

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

            //region click
            binding.btnAddCart.setOnClickListener(v -> {
                showSelectProductDialog(detailProductResponse);
            });

            binding.btnAdd.setOnClickListener(v -> {
                showSelectProductDialog(detailProductResponse);
            });
            //endregion
            //fixme check favourite product


            binding.btnCmt.setOnClickListener(v -> {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mViewModel.getCommentsAboutProduct(true);
                    if (sheetBehavior != null) {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
            });
        }
    }

    private void showSelectProductDialog(DetailProductResponse detailProductResponse) {
        SelectProductDialog selectProductDialog = new SelectProductDialog(getContext(), detailProductResponse);
        selectProductDialog.initData(detailProductResponse.getSizes(), detailProductResponse.getColors());
        selectProductDialog.setCancelable(true);
        selectProductDialog.show();
    }

    private void initLayoutComment() {
        sheetBehavior = BottomSheetBehavior.from(binding.layoutComment);
        commentAdapter = new CommentAdapter(getContext());
        sheetBehavior.setHideable(true);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        binding.rcvCmt.setAdapter(commentAdapter);
        commentAdapter.setLoadingMoreListener(this);

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        if (getActivity() != null) {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                        }
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        if (getActivity() != null) {
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        }
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        if (getActivity() != null) {
                            DeviceUtil.hideSoftKeyboard(getActivity());
                            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                        }
                    }
                    break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        mViewModel.getComments().observe(this, commentResponseListLoadmoreReponse -> {
            handleLoadMoreResponse(commentResponseListLoadmoreReponse, commentResponseListLoadmoreReponse.isRefresh(), commentResponseListLoadmoreReponse.isCanLoadmore());
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        commentAdapter.enableLoadingMore(canLoadmore);
        if (isRefresh) {
            commentAdapter.refresh(data);
        } else {
            commentAdapter.hideLoadingItem();
            commentAdapter.addModels(data, false);
        }
    }

    @Override
    public void onLoadMore() {
        commentAdapter.showLoadingItem(true);
        mViewModel.getCommentsAboutProduct(false);
    }
}
