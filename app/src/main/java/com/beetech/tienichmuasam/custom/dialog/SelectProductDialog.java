package com.beetech.tienichmuasam.custom.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ColorAdapter;
import com.beetech.tienichmuasam.adapter.SizeAdapter;
import com.beetech.tienichmuasam.custom.SpacesItemDecoration;
import com.beetech.tienichmuasam.databinding.LayoutSelectProductBinding;
import com.beetech.tienichmuasam.entity.response.ColorResponse;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.entity.response.SizeResponse;
import com.beetech.tienichmuasam.ui.product.DetaillProductViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class SelectProductDialog extends BottomSheetDialog {
    LayoutSelectProductBinding binding;
    DetaillProductViewModel mViewModel;
    private int currentSizeIndex = -1;
    private int currentColorIndex = -1;
    private DetailProductResponse detailProductResponse;


    public SelectProductDialog(@NonNull Context context, DetailProductResponse detailProductResponse) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_select_product, null, false);
        setContentView(binding.getRoot());
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.detailProductResponse = detailProductResponse;
        init();
        initListener();
    }

    private void init() {
        if (detailProductResponse != null) {
            binding.tvProductName.setText(detailProductResponse.getName());
        }
    }

    private void initListener() {
        binding.btnBack.setOnClickListener(v -> {
            hide();
        });
    }

    public void initData(List<SizeResponse> sizes, List<ColorResponse> colors) {
        for (ColorResponse colorResponse : colors) {
            colorResponse.setIsSelected(false);
        }

        ColorAdapter colorAdapter = new ColorAdapter(getContext(), colors);
        binding.rcvColor.setAdapter(colorAdapter);
        binding.rcvColor.addItemDecoration(new SpacesItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.margin_6dp)));
        binding.rcVSize.addItemDecoration(new SpacesItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.margin_6dp)));
        colorAdapter.setListener(position -> {
            currentSizeIndex = -1;
            for (SizeResponse sizeResponse : sizes) {
                sizeResponse.setSelected(false);
            }
            binding.layoutSize.setVisibility(View.VISIBLE);
            SizeAdapter sizeAdapter = new SizeAdapter(getContext(), colors.get(position).getListSizes(sizes));
            binding.rcVSize.setAdapter(sizeAdapter);

            sizeAdapter.setListener(pos -> {
                if (currentSizeIndex != -1) {
                    sizes.get(currentSizeIndex).setSelected(false);
                    sizeAdapter.notifyItemChanged(currentSizeIndex);
                }
                currentSizeIndex = pos;
                sizes.get(currentSizeIndex).setSelected(true);
                sizeAdapter.notifyItemChanged(currentSizeIndex);
            });
            if (currentColorIndex != -1) {
                colors.get(currentColorIndex).setIsSelected(false);
                colorAdapter.notifyItemChanged(currentColorIndex);
            }
            currentColorIndex = position;
            colors.get(currentColorIndex).setIsSelected(true);
            colorAdapter.notifyItemChanged(currentColorIndex);
        });
    }
}
