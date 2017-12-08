package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/17/2017 AD.
 */

public class ProductInCartItemCollectionDao implements Parcelable{

    @SerializedName("status")           private String status;
    @SerializedName("productInCart")    private List<ProductInCartItemDao> productInCart = null;
    @SerializedName("productSummary")   private String productSummary;

    protected ProductInCartItemCollectionDao(Parcel in) {
        status = in.readString();
        productInCart = in.createTypedArrayList(ProductInCartItemDao.CREATOR);
        productSummary = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(productInCart);
        dest.writeString(productSummary);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductInCartItemCollectionDao> CREATOR = new Creator<ProductInCartItemCollectionDao>() {
        @Override
        public ProductInCartItemCollectionDao createFromParcel(Parcel in) {
            return new ProductInCartItemCollectionDao(in);
        }

        @Override
        public ProductInCartItemCollectionDao[] newArray(int size) {
            return new ProductInCartItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductInCartItemDao> getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(List<ProductInCartItemDao> productInCart) {
        this.productInCart = productInCart;
    }

    public String getProductIncartSummarys() {
        return productSummary;
    }

    public void setProductIncartSummarys(String productSummary) {
        this.productSummary = productSummary;
    }
}
