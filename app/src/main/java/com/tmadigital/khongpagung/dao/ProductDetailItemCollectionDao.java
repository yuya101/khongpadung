package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/11/2017 AD.
 */

public class ProductDetailItemCollectionDao implements Parcelable {

    @SerializedName("status")           private String status;
    @SerializedName("Product Detail")   private List<ProductDetailItemDao> productDetail = null;
    @SerializedName("Review")           private List<ProductDetailReviewItemDao> review = null;

    protected ProductDetailItemCollectionDao(Parcel in) {
        status = in.readString();
        productDetail = in.createTypedArrayList(ProductDetailItemDao.CREATOR);
        review = in.createTypedArrayList(ProductDetailReviewItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(productDetail);
        dest.writeTypedList(review);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailItemCollectionDao> CREATOR = new Creator<ProductDetailItemCollectionDao>() {
        @Override
        public ProductDetailItemCollectionDao createFromParcel(Parcel in) {
            return new ProductDetailItemCollectionDao(in);
        }

        @Override
        public ProductDetailItemCollectionDao[] newArray(int size) {
            return new ProductDetailItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetailItemDao> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailItemDao> productDetail) {
        this.productDetail = productDetail;
    }

    public List<ProductDetailReviewItemDao> getReview() {
        return review;
    }

    public void setReview(List<ProductDetailReviewItemDao> review) {
        this.review = review;
    }
}
