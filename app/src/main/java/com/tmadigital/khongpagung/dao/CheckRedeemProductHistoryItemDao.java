package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public class CheckRedeemProductHistoryItemDao implements Parcelable {
    @SerializedName("status")           private String status;
    @SerializedName("orderid")          private String orderid;
    @SerializedName("proid")            private String proid;
    @SerializedName("name")             private String name;
    @SerializedName("price")            private String price;
    @SerializedName("pictureName")      private String pictureName;
    @SerializedName("picture")          private String picture;
    @SerializedName("starRate")         private Integer starRate;
    @SerializedName("orderdate")        private String orderdate;
    @SerializedName("ordertime")        private String ordertime;
    @SerializedName("sendproductflag")  private String sendproductflag;
    @SerializedName("reviewstatus")     private String reviewstatus;
    @SerializedName("reviewMessage")    private String reviewMessage;

    protected CheckRedeemProductHistoryItemDao(Parcel in) {
        status = in.readString();
        orderid = in.readString();
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        pictureName = in.readString();
        picture = in.readString();
        orderdate = in.readString();
        ordertime = in.readString();
        sendproductflag = in.readString();
        reviewstatus = in.readString();
        reviewMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(orderid);
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(pictureName);
        dest.writeString(picture);
        dest.writeString(orderdate);
        dest.writeString(ordertime);
        dest.writeString(sendproductflag);
        dest.writeString(reviewstatus);
        dest.writeString(reviewMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckRedeemProductHistoryItemDao> CREATOR = new Creator<CheckRedeemProductHistoryItemDao>() {
        @Override
        public CheckRedeemProductHistoryItemDao createFromParcel(Parcel in) {
            return new CheckRedeemProductHistoryItemDao(in);
        }

        @Override
        public CheckRedeemProductHistoryItemDao[] newArray(int size) {
            return new CheckRedeemProductHistoryItemDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getSendproductflag() {
        return sendproductflag;
    }

    public void setSendproductflag(String sendproductflag) {
        this.sendproductflag = sendproductflag;
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }
}
