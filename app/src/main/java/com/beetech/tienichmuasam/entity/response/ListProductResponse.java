package com.beetech.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class ListProductResponse {

    @SerializedName("sizeId")
    private String sizeId;

    @SerializedName("productId")
    private String productId;

    @SerializedName("like")
    private int like;

    @SerializedName("colorId")
    private String colorId;

    @SerializedName("imagePath")
    private String imagePath;

    @SerializedName("priceDiscountStr")
    private String priceDiscountStr;

    @SerializedName("discount")
    private int discount;

    @SerializedName("priceDiscount")
    private double priceDiscount;

    @SerializedName("totalSellCount")
    private int totalSellCount;

    @SerializedName("priceStr")
    private String priceStr;

    @SerializedName("totalBuyCount")
    private int totalBuyCount;

    @SerializedName("price")
    private double price;

    @SerializedName("name")
    private String name;

    @SerializedName("stockNum")
    private int stockNum;

    @SerializedName("views")
    private int views;

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setPriceDiscountStr(String priceDiscountStr) {
        this.priceDiscountStr = priceDiscountStr;
    }

    public String getPriceDiscountStr() {
        return priceDiscountStr;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setTotalSellCount(int totalSellCount) {
        this.totalSellCount = totalSellCount;
    }

    public int getTotalSellCount() {
        return totalSellCount;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setTotalBuyCount(int totalBuyCount) {
        this.totalBuyCount = totalBuyCount;
    }

    public int getTotalBuyCount() {
        return totalBuyCount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }



    @Override
    public String toString() {
        return
                "ListProductResponse{" +
                        "sizeId = '" + sizeId + '\'' +
                        ",productId = '" + productId + '\'' +
                        ",like = '" + like + '\'' +
                        ",colorId = '" + colorId + '\'' +
                        ",imagePath = '" + imagePath + '\'' +
                        ",priceDiscountStr = '" + priceDiscountStr + '\'' +
                        ",discount = '" + discount + '\'' +
                        ",priceDiscount = '" + priceDiscount + '\'' +
                        ",totalSellCount = '" + totalSellCount + '\'' +
                        ",priceStr = '" + priceStr + '\'' +
                        ",totalBuyCount = '" + totalBuyCount + '\'' +
                        ",price = '" + price + '\'' +
                        ",name = '" + name + '\'' +
                        ",stockNum = '" + stockNum + '\'' +
                        ",views = '" + views + '\'' +
                        "}";
    }
}