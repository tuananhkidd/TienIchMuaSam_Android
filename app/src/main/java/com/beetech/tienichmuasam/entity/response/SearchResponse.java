package com.beetech.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {
    private int id;
    private String name;
    @SerializedName("regular_price")
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
