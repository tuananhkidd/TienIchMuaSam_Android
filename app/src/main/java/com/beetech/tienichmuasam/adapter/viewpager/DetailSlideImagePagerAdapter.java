package com.beetech.tienichmuasam.adapter.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.beetech.tienichmuasam.BuildConfig;
import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.entity.response.ImageProductResponse;
import com.beetech.tienichmuasam.ui.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class DetailSlideImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageProductResponse> listItems;

    public DetailSlideImagePagerAdapter(Context context, List<ImageProductResponse> listItems) {
        this.context = context;
        this.listItems = new ArrayList<>();
        this.listItems.addAll(listItems);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_product, container, false);
        GlideApp.with(context)
                .load(BuildConfig.IMAGE_URL+listItems.get(position).getImagePath())
                .into((ImageView) view.findViewById(R.id.img));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ViewPager viewGroup = (ViewPager) container;
        View view = (View) object;
        viewGroup.removeView(view);
    }

}
