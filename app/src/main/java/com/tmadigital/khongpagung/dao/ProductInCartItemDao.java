package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/17/2017 AD.
 */

public class ProductInCartItemDao implements Parcelable {

    @SerializedName("proid")            private String proid;
    @SerializedName("name")             private String name;
    @SerializedName("price")            private String price;
    @SerializedName("memberPoint")      private Integer memberPoint;
    @SerializedName("pictureName")      private String pictureName;
    @SerializedName("picture")          private String picture;
    @SerializedName("starRate")         private Integer starRate;

    protected ProductInCartItemDao(Parcel in) {
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        pictureName = in.readString();
        picture = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(pictureName);
        dest.writeString(picture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductInCartItemDao> CREATOR = new Creator<ProductInCartItemDao>() {
        @Override
        public ProductInCartItemDao createFromParcel(Parcel in) {
            return new ProductInCartItemDao(in);
        }

        @Override
        public ProductInCartItemDao[] newArray(int size) {
            return new ProductInCartItemDao[size];
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

    public Integer getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(Integer memberPoint) {
        this.memberPoint = memberPoint;
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
