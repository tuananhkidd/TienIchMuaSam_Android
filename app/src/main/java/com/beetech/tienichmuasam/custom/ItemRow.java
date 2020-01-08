package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.beetech.tienichmuasam.R;

public class ItemRow extends CustomViewConstraintLayout {
    private ImageView imgLogo;
    private ImageView imgAction;
    private TextView tvTitle;

    public ItemRow(Context context) {
        super(context);
    }

    public ItemRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayoutRes() {
        return R.layout.item_row;
    }

    @Override
    int[] getStyableRes() {
        return R.styleable.ItemRow;
    }

    @Override
    void initView() {
        imgAction = findViewById(R.id.img_action);
        imgLogo = findViewById(R.id.img_logo);
        tvTitle = findViewById(R.id.tv_title);
    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {

    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
        Drawable icLogo = mTypedArray.getDrawable(R.styleable.ItemRow_item_icon);
        if (icLogo != null) {
            imgLogo.setVisibility(VISIBLE);
            imgLogo.setImageDrawable(icLogo);
        }else {
            imgLogo.setVisibility(GONE);
        }
        Drawable icAction = mTypedArray.getDrawable(R.styleable.ItemRow_item_action);
        if (icAction != null) {
            imgAction.setVisibility(VISIBLE);
            imgAction.setImageDrawable(icAction);
        } else {
            imgAction.setVisibility(GONE);
        }

        String title = mTypedArray.getString(R.styleable.ItemRow_item_title);
        tvTitle.setText(title);

    }
}
