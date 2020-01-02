package com.beetech.tienichmuasam.entity.response;

import androidx.databinding.BindingAdapter;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.ui.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentResponse {
    private String userId;
    private String productId;
    private String createdDate;
    private String content;
    private float rating;
    private String userAvatar;
    private String userName;

    @BindingAdapter({"android:image"})
    public static void loadImage(CircleImageView view, String avatarUrl) {
        GlideApp.with(view.getContext())
                .load(avatarUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .circleCrop()
                .into(view);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
