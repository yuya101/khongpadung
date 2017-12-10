package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus on 12/10/2017 AD.
 */

public class ProductDetailNewCollectionItemDao implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Product Detail")
    @Expose
    private List<ProductDetailNewItemDao> productDetail = null;
    @SerializedName("Category Header")
    @Expose
    private List<ProductDetailNewHeaderItemDao> categoryHeader = null;
    @SerializedName("Category Description")
    @Expose
    private List<ProductDetailNewDescriptionItemDao> categoryDescription = null;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;

    protected ProductDetailNewCollectionItemDao(Parcel in) {
        status = in.readString();
        productDetail = in.createTypedArrayList(ProductDetailNewItemDao.CREATOR);
        categoryHeader = in.createTypedArrayList(ProductDetailNewHeaderItemDao.CREATOR);
        categoryDescription = in.createTypedArrayList(ProductDetailNewDescriptionItemDao.CREATOR);
        productDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(productDetail);
        dest.writeTypedList(categoryHeader);
        dest.writeTypedList(categoryDescription);
        dest.writeString(productDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailNewCollectionItemDao> CREATOR = new Creator<ProductDetailNewCollectionItemDao>() {
        @Override
        public ProductDetailNewCollectionItemDao createFromParcel(Parcel in) {
            return new ProductDetailNewCollectionItemDao(in);
        }

        @Override
        public ProductDetailNewCollectionItemDao[] newArray(int size) {
            return new ProductDetailNewCollectionItemDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetailNewItemDao> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailNewItemDao> productDetail) {
        this.productDetail = productDetail;
    }

    public List<ProductDetailNewHeaderItemDao> getCategoryHeader() {
        return categoryHeader;
    }

    public void setCategoryHeader(List<ProductDetailNewHeaderItemDao> categoryHeader) {
        this.categoryHeader = categoryHeader;
    }

    public List<ProductDetailNewDescriptionItemDao> getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(List<ProductDetailNewDescriptionItemDao> categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
