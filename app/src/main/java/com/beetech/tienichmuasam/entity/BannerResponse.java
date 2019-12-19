package com.beetech.tienichmuasam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cover_url")
    @Expose
    private String imgUrl;
    @SerializedName("title")
    @Expose
    private String title;

    public BannerResponse( String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
