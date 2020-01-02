package com.beetech.tienichmuasam.custom.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.DialogImageDetailBinding;
import com.beetech.tienichmuasam.ui.GlideApp;
import com.beetech.tienichmuasam.utils.Constant;


public class ImageDetailDialog extends DialogFragment {

    DialogImageDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = DataBindingUtil.inflate(inflater,R.layout.dialog_image_detail,container,false);

        createFullScreenDialogFragment();

        binding.btnBack.setOnClickListener(view -> dismiss());
        return binding.getRoot();
    }





    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().windowAnimations = R.style.ImageDetailDialogStyle;

        Bundle bundle = getArguments();
        if(bundle!=null){
            String path = bundle.getString(Constant.IMAGE_DETAIL);
            GlideApp.with(this)
                    .load(path)
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(binding.dialogImageView);
        }
    }

    /**
     * Use for show full screen ImageViewZoom
     */
    public void createFullScreenDialogFragment() {
        RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setContentView(root);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }




}
