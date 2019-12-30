package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beetech.tienichmuasam.R;
import com.bumptech.glide.Glide;

public class BaseToolbar extends RelativeLayout {
    private Context context;
    private ImageView btnBack;
    private ImageView btnRight;
    private TextView tvTitle;
    private OnClickLeftListener leftListener;
    private OnClickRightListener rightListener;

    public void setLeftListener(OnClickLeftListener leftListener) {
        this.leftListener = leftListener;
    }

    public void setRightListener(OnClickRightListener rightListener) {
        this.rightListener = rightListener;
    }

    public BaseToolbar(Context context) {
        super(context);
        init(context);
    }

    public BaseToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setParams(attrs);
    }

    public BaseToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setParams(attrs);
    }

    private void init(Context context) {
        this.context = context;
    }

    private void setParams(AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BaseToolbar, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_base_toolbar, this, true);
        btnBack = view.findViewById(R.id.btn_left);
        btnRight = view.findViewById(R.id.btn_right);
        tvTitle = view.findViewById(R.id.tv_title);

        String title = a.getString(R.styleable.BaseToolbar_bt_title);
        tvTitle.setText(title);
        Drawable btnBackRs = a.getDrawable(R.styleable.BaseToolbar_bt_ic_left);
        btnBack.setImageDrawable(btnBackRs);
        Drawable btnRightRs = a.getDrawable(R.styleable.BaseToolbar_bt_ic_left);
        Glide.with(context)
                .load(btnRightRs)
                .into(btnRight);

        btnBack.setOnClickListener(v -> {
            if (leftListener != null) {
                leftListener.onClickLeft();
            }
        });

        btnRight.setOnClickListener(v -> {
            if (rightListener != null) {
                rightListener.onClickRight();
            }
        });
    }

    public interface OnClickLeftListener {
        void onClickLeft();
    }


    public interface OnClickRightListener {
        void onClickRight();
    }
}
