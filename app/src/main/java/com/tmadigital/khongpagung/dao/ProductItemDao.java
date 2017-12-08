package com.tmadigital.khongpagung.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 3/2/2017 AD.
 */

public class ProductItemDao {
    @SerializedName("status")      private String status;
    @SerializedName("proid")       private String proid;
    @SerializedName("name")        private String name;
    @SerializedName("price")       private String price;
    @SerializedName("pictureName") private String pictureName;
    @SerializedName("picture")     private String picture;
    @SerializedName("starRate")    private Integer starRate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
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

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getStarRate() {
        return starRate;
    }

    public void setStarRate(Integer starRate) {
        this.starRate = starRate;
    }
}
