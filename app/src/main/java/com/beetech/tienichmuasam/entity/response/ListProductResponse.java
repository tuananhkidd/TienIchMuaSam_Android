package com.beetech.tienichmuasam.entity.response;

import android.text.Html;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.beetech.tienichmuasam.BuildConfig;
import com.beetech.tienichmuasam.R;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListProductResponse {

    @SerializedName("price")
    private BigDecimal regularPrice;

    @SerializedName("priceDiscount")
    private BigDecimal sellerPrice;

    @SerializedName("imagePath")
    private String avatarUrl;

    @SerializedName("like")
    private int favouriteCount;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("priceStr")
    private String priceStr;

    @SerializedName("priceDiscountStr")
    private String priceDiscountStr;

    @SerializedName("view")
    private String view;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getPriceDiscountStr() {
        return priceDiscountStr;
    }

    public void setPriceDiscountStr(String priceDiscountStr) {
        this.priceDiscountStr = priceDiscountStr;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setSellerPrice(BigDecimal sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public BigDecimal getSellerPrice() {
        return sellerPrice;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setFavouriteCount(int favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @BindingAdapter("android:price")
    public static void showPrice(TextView tv, String price) {
        if (price != null) {
            tv.setText(Html.fromHtml("<del>" + price + "</del>"));
        }
    }

    @BindingAdapter({"android:image"})
    public static void loadImage(CircleImageView view, String avatarUrl) {
        Glide.with(view.getContext())
                .load(BuildConfig.IMAGE_URL + avatarUrl)
//                .load(avatarUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .circleCrop()
                .into(view);
    }

    @Override
    public String toString() {
        return
                "ListProductResponse{" +
                        "regular_price = '" + regularPrice + '\'' +
                        ",seller_price = '" + sellerPrice + '\'' +
                        ",avatar_url = '" + avatarUrl + '\'' +
                        ",favourite_count = '" + favouriteCount + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}