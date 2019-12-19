package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class InfinifiAdapter<T> extends PagerAdapter {
    protected List<T> mListItems;
    protected Context mContext;
    protected OnItemClickListener<T> mListener;

    public InfinifiAdapter(Context context, List<T> listItems) {
        mContext = context;
        if (listItems == null || (listItems.isEmpty()))
            mListItems = new ArrayList<>();
        else
            mListItems = listItems;
    }

    public void addData(List<T> listItems) {
        if (listItems != null && (listItems.isEmpty()))
            mListItems.addAll(listItems);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListItems.size() * 1000;
    }

    public int getRealCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return onActualCreateItem(container, position);
    }

    @NonNull
    protected Object onActualCreateItem(@NonNull ViewGroup container, int position) {
        return null;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewGroup = (ViewPager) container;
        View view = (View) object;
        viewGroup.removeView(view);
    }

    public void setListener(OnItemClickListener<T> onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onClickItem(int pos, T data);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
