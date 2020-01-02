package com.beetech.tienichmuasam.ui.image_detail;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetech.tienichmuasam.R;

public class ImageDetailFragment extends Fragment {

    private ImageDetailViewModel mViewModel;

    public static ImageDetailFragment newInstance() {
        return new ImageDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ImageDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}
