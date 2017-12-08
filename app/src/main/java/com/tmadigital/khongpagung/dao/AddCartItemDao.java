package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/16/2017 AD.
 */

public class AddCartItemDao implements Parcelable {
    @SerializedName("status")       private String status;
    @SerializedName("productInCart")   private String productInCart;

    protected AddCartItemDao(Parcel in) {
        status = in.readString();
        productInCart = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(productInCart);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddCartItemDao> CREATOR = new Creator<AddCartItemDao>() {
        @Override
        public AddCartItemDao createFromParcel(Parcel in) {
            return new AddCartItemDao(in);
        }

        @Override
        public AddCartItemDao[] newArray(int size) {
            return new AddCartItemDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(String productInCart) {
        this.productInCart = productInCart;
    }
}
