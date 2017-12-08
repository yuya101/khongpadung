package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/10/2017 AD.
 */

public class ProductNearGoneItemDao implements Parcelable {
    @SerializedName("proid")         private String proid;
    @SerializedName("name")         private String name;
    @SerializedName("price")        private String price;
    @SerializedName("pictureName")  private String pictureName;
    @SerializedName("picture")      private String picture;
    @SerializedName("starRate")     private int starRate;


    protected ProductNearGoneItemDao(Parcel in) {
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        pictureName = in.readString();
        picture = in.readString();
        starRate = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(pictureName);
        dest.writeString(picture);
        dest.writeInt(starRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductNearGoneItemDao> CREATOR = new Creator<ProductNearGoneItemDao>() {
        @Override
        public ProductNearGoneItemDao createFromParcel(Parcel in) {
            return new ProductNearGoneItemDao(in);
        }

        @Override
        public ProductNearGoneItemDao[] newArray(int size) {
            return new ProductNearGoneItemDao[size];
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

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }
}
