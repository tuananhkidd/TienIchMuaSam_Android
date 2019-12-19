package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.github.demono.AutoScrollViewPager;


public class InfinitiViewPager extends AutoScrollViewPager {


    public InfinitiViewPager(Context context) {
        super(context);
    }

    public InfinitiViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getRealCurrentItem() {
        if (getAdapter() == null || getAdapter().getCount() == 0)
            return 0;
        if (getAdapter() instanceof InfinifiAdapter) {
            return getCurrentItem() % ((InfinifiAdapter) getAdapter()).getRealCount();
        } else {
            return super.getCurrentItem();
        }
    }
}
