package com.beetech.tienichmuasam.utils;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

public class TextViewUtil {
    public static Spanned getInActivePrice(String price) {
        if (TextUtils.isEmpty(price)) {
            return new SpannableString("");
        }
        return Html.fromHtml("<del>" + price + "</del>");
    }
}
