package com.beetech.tienichmuasam.adapter.viewpager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.custom.InfinifiAdapter;
import com.beetech.tienichmuasam.entity.BannerResponse;
import com.beetech.tienichmuasam.utils.UIUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class NewsFeedSlideAdapter extends InfinifiAdapter<BannerResponse> {
    private int width;
    private int height;
    RequestOptions options = new RequestOptions();

    public NewsFeedSlideAdapter(Context context, List<BannerResponse> listItems) {
        super(context, listItems);
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        // padding left, right and shadow
        width = displayMetrics.widthPixels - (2 * (int) (context.getResources().getDimension(R.dimen.margin_20dp) ));
//        width = displayMetrics.widthPixels - 2 * (int) UIUtil.convertDpToPixel(20, mContext);
        height = (int) UIUtil.convertDpToPixel(144, mContext);
//        options.transforms(new CenterCrop(), new RoundedCorners((int) UIUtil.convertDpToPixel(10, mContext)));
        options.placeholder(R.drawable.img_no_image);
        options.error(R.drawable.img_no_image);
        options.override(width, height);
        options.dontAnimate();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    @NonNull
    @Override
    protected Object onActualCreateItem(@NonNull ViewGroup container, int position) {
        int realPos = position % getRealCount();
        BannerResponse item = mListItems.get(realPos);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_top_newsfeed, container, false);
        ImageView imageView = view.findViewById(R.id.ivBackground);
        Glide.with(mContext).load(item.getImgUrl()).apply(options).into(imageView);
//        Glide.with(mContext).load(R.drawable.img_splash).apply(options).into(imageView);
        container.addView(view);
        view.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onClickItem(realPos, item);
        });
        Log.d("Test", "Pos: " + position);
        return view;
    }

}
