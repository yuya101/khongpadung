package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/11/2017 AD.
 */

public class ProductDetailItemDao implements Parcelable {
    @SerializedName("proid")        private String proid;
    @SerializedName("name")         private String name;
    @SerializedName("price")        private String price;
    @SerializedName("property")     private String property;
    @SerializedName("pictureName")  private String pictureName;
    @SerializedName("picture")      private String picture;
    @SerializedName("starRate")     private Integer starRate;
    @SerializedName("productClickCount")     private Integer clickCount;

    protected ProductDetailItemDao(Parcel in) {
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        property = in.readString();
        pictureName = in.readString();
        picture = in.readString();
    }

    public static final Creator<ProductDetailItemDao> CREATOR = new Creator<ProductDetailItemDao>() {
        @Override
        public ProductDetailItemDao createFromParcel(Parcel in) {
            return new ProductDetailItemDao(in);
        }

        @Override
        public ProductDetailItemDao[] newArray(int size) {
            return new ProductDetailItemDao[size];
        }
    };

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
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

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(property);
        dest.writeString(pictureName);
        dest.writeString(picture);
    }
}
