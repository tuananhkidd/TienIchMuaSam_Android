package com.beetech.tienichmuasam.entity;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryResponse {
    private int id;
    private String category;
    private String url;
    @DrawableRes
    private int logo;

    public CategoryResponse(int id,@DrawableRes int logo, String category) {
        this.id = id;
        this.logo = logo;
        this.category = category;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @BindingAdapter({"android:image"})
    public static void loadImage(CircleImageView view, int logo) {
        Glide.with(view.getContext())
                .load(logo)
                .into(view);

        // If you consider Picasso, follow the below
        // Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(view);
    }
}
