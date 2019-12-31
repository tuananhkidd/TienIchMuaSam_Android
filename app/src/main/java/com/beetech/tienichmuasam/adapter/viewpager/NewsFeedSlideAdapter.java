package com.beetech.tienichmuasam.adapter.viewpager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.entity.response.BannerResponse;
import com.beetech.tienichmuasam.utils.UIUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedSlideAdapter extends LoopingPagerAdapter<BannerResponse> {
    private int width;
    private int height;
    private List<BannerResponse> listItems;
    private Context context;
    RequestOptions options = new RequestOptions();

    public NewsFeedSlideAdapter(Context context, List<BannerResponse> listItems) {
        super(context,listItems,true);
        this.context = context;
        this.listItems = new ArrayList<>();
        this.listItems.addAll(listItems);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        // padding left, right and shadow
        width = displayMetrics.widthPixels - (2 * (int) (context.getResources().getDimension(R.dimen.margin_20dp) ));
//        width = displayMetrics.widthPixels - 2 * (int) UIUtil.convertDpToPixel(20, mContext);
        height = (int) UIUtil.convertDpToPixel(144, context);
//        options.transforms(new CenterCrop(), new RoundedCorners((int) UIUtil.convertDpToPixel(10, mContext)));
        options.placeholder(R.drawable.img_no_image);
        options.error(R.drawable.img_no_image);
        options.override(width, height);
        options.dontAnimate();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ViewPager viewGroup = (ViewPager) container;
        View view = (View) object;
        viewGroup.removeView(view);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_newsfeed, container, false);
        return view;
    }

    @Override
    protected void bindView(View view, int listPosition, int viewType) {
        ImageView imageView = view.findViewById(R.id.ivBackground);
        BannerResponse item = listItems.get(listPosition);
        Glide.with(context).load(item.getImgUrl()).apply(options).into(imageView);
    }
}
