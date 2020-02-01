package com.beetech.tienichmuasam.adapter.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.beetech.tienichmuasam.R;

import java.util.ArrayList;
import java.util.List;

public class IntroducePagerAdapter extends PagerAdapter {
    private Context ctx;
    private List<Integer> layouts;

    public IntroducePagerAdapter(Context ctx) {
        this.ctx = ctx;
        this.layouts = new ArrayList<>();
        layouts.add(R.layout.welcome_slide1);
        layouts.add(R.layout.welcome_slide2);
        layouts.add(R.layout.welcome_slide3);
        layouts.add(R.layout.welcome_slide4);
    }

    @Override
    public int getCount() {
        return layouts.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(layouts.get(position), container, false);
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
