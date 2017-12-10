package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus on 12/10/2017 AD.
 */

public class ProductDetailNewItemDao implements Parcelable {
    @SerializedName("proid")
    @Expose
    private String proid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("property")
    @Expose
    private String property;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("qrcode")
    @Expose
    private String qrcode;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("pictureName1")
    @Expose
    private String pictureName1;
    @SerializedName("picture1")
    @Expose
    private String picture1;
    @SerializedName("picture2")
    @Expose
    private String picture2;
    @SerializedName("pictureName2")
    @Expose
    private String pictureName2;
    @SerializedName("picture3")
    @Expose
    private String picture3;
    @SerializedName("pictureName3")
    @Expose
    private String pictureName3;
    @SerializedName("picture4")
    @Expose
    private String picture4;
    @SerializedName("pictureName4")
    @Expose
    private String pictureName4;
    @SerializedName("picture5")
    @Expose
    private String picture5;
    @SerializedName("pictureName5")
    @Expose
    private String pictureName5;
    @SerializedName("starRate")
    @Expose
    private Integer starRate;
    @SerializedName("productClickCount")
    @Expose
    private String productClickCount;

    protected ProductDetailNewItemDao(Parcel in) {
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        property = in.readString();
        productCode = in.readString();
        qrcode = in.readString();
        companyName = in.readString();
        pictureName1 = in.readString();
        picture1 = in.readString();
        picture2 = in.readString();
        pictureName2 = in.readString();
        picture3 = in.readString();
        pictureName3 = in.readString();
        picture4 = in.readString();
        pictureName4 = in.readString();
        picture5 = in.readString();
        pictureName5 = in.readString();
        if (in.readByte() == 0) {
            starRate = null;
        } else {
            starRate = in.readInt();
        }
        productClickCount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(property);
        dest.writeString(productCode);
        dest.writeString(qrcode);
        dest.writeString(companyName);
        dest.writeString(pictureName1);
        dest.writeString(picture1);
        dest.writeString(picture2);
        dest.writeString(pictureName2);
        dest.writeString(picture3);
        dest.writeString(pictureName3);
        dest.writeString(picture4);
        dest.writeString(pictureName4);
        dest.writeString(picture5);
        dest.writeString(pictureName5);
        if (starRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(starRate);
        }
        dest.writeString(productClickCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailNewItemDao> CREATOR = new Creator<ProductDetailNewItemDao>() {
        @Override
        public ProductDetailNewItemDao createFromParcel(Parcel in) {
            return new ProductDetailNewItemDao(in);
        }

        @Override
        public ProductDetailNewItemDao[] newArray(int size) {
            return new ProductDetailNewItemDao[size];
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPictureName1() {
        return pictureName1;
    }

    public void setPictureName1(String pictureName1) {
        this.pictureName1 = pictureName1;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPictureName2() {
        return pictureName2;
    }

    public void setPictureName2(String pictureName2) {
        this.pictureName2 = pictureName2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPictureName3() {
        return pictureName3;
    }

    public void setPictureName3(String pictureName3) {
        this.pictureName3 = pictureName3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPictureName4() {
        return pictureName4;
    }

    public void setPictureName4(String pictureName4) {
        this.pictureName4 = pictureName4;
    }

    public String getPicture5() {
        return picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }

    public String getPictureName5() {
        return pictureName5;
    }

    public void setPictureName5(String pictureName5) {
        this.pictureName5 = pictureName5;
    }

    public Integer getStarRate() {
        return starRate;
    }

    public void setStarRate(Integer starRate) {
        this.starRate = starRate;
    }

    public String getProductClickCount() {
        return productClickCount;
    }

    public void setProductClickCount(String productClickCount) {
        this.productClickCount = productClickCount;
    }
}
