package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.beetech.tienichmuasam.R;

public class QuantitySelectedCustomView extends CustomViewConstraintLayout {
    private static final int MIN_ITEMS = 1;
    private int maxItems;
    private int defaultItems;
    private ImageView btnRemove;
    private ImageView btnAdd;
    private TextView tvQuantity;

    public QuantitySelectedCustomView(Context context) {
        super(context);
    }

    public QuantitySelectedCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuantitySelectedCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayoutRes() {
        return R.layout.layout_quantity_selected;
    }

    @Override
    int[] getStyableRes() {
        return R.styleable.QuantitySelected;
    }

    @Override
    void initView() {
        btnRemove = view.findViewById(R.id.btn_remove);
        btnAdd = view.findViewById(R.id.btn_add);
        tvQuantity = view.findViewById(R.id.tv_quantity);
        tvQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Integer.valueOf(editable.toString()) == MIN_ITEMS) {
                    btnRemove.setAlpha(0.6f);
                    btnRemove.setEnabled(false);
                    return;
                }
                if (Integer.valueOf(editable.toString()) == maxItems) {
                    btnAdd.setAlpha(0.6f);
                    btnAdd.setEnabled(false);
                    return;
                }
                btnRemove.setAlpha(1f);
                btnRemove.setEnabled(true);
                btnAdd.setAlpha(1f);
                btnAdd.setEnabled(true);
            }
        });
    }

    @Override
    void initListener() {
        btnRemove.setOnClickListener(view -> {
            removeItem();
        });
        btnAdd.setOnClickListener(view -> {
            addItem();
        });
    }

    private void addItem() {
        tvQuantity.setText(String.valueOf(Integer.valueOf(tvQuantity.getText().toString()) + 1));
    }

    private void removeItem() {
        tvQuantity.setText(String.valueOf(Integer.valueOf(tvQuantity.getText().toString()) - 1));
    }

    @Override
    void initData() {

    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
        maxItems = mTypedArray.getInt(R.styleable.QuantitySelected_qs_max_items, 5);
        defaultItems = mTypedArray.getInt(R.styleable.QuantitySelected_qs_default_items, 1);
        if (defaultItems < maxItems && defaultItems > 0) {
            setDefaultItems(defaultItems);
        } else {
            defaultItems = 1;
            setDefaultItems(1);
        }
    }

    private void setDefaultItems(int defaultItems) {
        tvQuantity.setText(String.valueOf(defaultItems));
    }
}
