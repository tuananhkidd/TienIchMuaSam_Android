package com.beetech.tienichmuasam.custom.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.annotation.NonNull;

import com.beetech.tienichmuasam.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SelectProductDialog extends BottomSheetDialog {

    public SelectProductDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_select_product);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setBackgroundDrawableResource(R.drawable.bg_home_round);
    }
}
