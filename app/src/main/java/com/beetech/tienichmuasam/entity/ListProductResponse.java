package com.beetech.tienichmuasam.entity;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.beetech.tienichmuasam.utils.NumberUtils;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListProductResponse {

    @SerializedName("regular_price")
    private BigDecimal regularPrice;

    @SerializedName("seller_price")
    private BigDecimal sellerPrice;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("favourite_count")
    private int favouriteCount;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

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
    public static void showPrice(TextView tv, BigDecimal price) {
        if (price != null) {
            tv.setText(NumberUtils.fomartMoney(price));
        }
    }

    @BindingAdapter({"android:image"})
    public static void loadImage(CircleImageView view, String avatarUrl) {
        Glide.with(view.getContext())
                .load(avatarUrl)
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