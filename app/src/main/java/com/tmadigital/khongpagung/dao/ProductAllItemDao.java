package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/10/2017 AD.
 */

public class ProductAllItemDao implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("qrcode")
    @Expose
    private String qrcode;
    @SerializedName("proid")
    @Expose
    private String proid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("productDetail")
    @Expose
    private String productDetail;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("pictureName")
    @Expose
    private String pictureName;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("starRate")
    @Expose
    private Integer starRate;

    protected ProductAllItemDao(Parcel in) {
        status = in.readString();
        qrcode = in.readString();
        proid = in.readString();
        name = in.readString();
        productDetail = in.readString();
        productCode = in.readString();
        price = in.readString();
        companyName = in.readString();
        pictureName = in.readString();
        picture = in.readString();
        if (in.readByte() == 0) {
            starRate = null;
        } else {
            starRate = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(qrcode);
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(productDetail);
        dest.writeString(productCode);
        dest.writeString(price);
        dest.writeString(companyName);
        dest.writeString(pictureName);
        dest.writeString(picture);
        if (starRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(starRate);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductAllItemDao> CREATOR = new Creator<ProductAllItemDao>() {
        @Override
        public ProductAllItemDao createFromParcel(Parcel in) {
            return new ProductAllItemDao(in);
        }

        @Override
        public ProductAllItemDao[] newArray(int size) {
            return new ProductAllItemDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
