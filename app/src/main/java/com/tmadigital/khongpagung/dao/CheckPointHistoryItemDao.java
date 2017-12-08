package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public class CheckPointHistoryItemDao implements Parcelable {
    @SerializedName("orderid")                  private String orderid;
    @SerializedName("proid")                    private String proid;
    @SerializedName("name")                     private String name;
    @SerializedName("price")                    private String price;
    @SerializedName("pictureName")              private String pictureName;
    @SerializedName("picture")                  private String picture;
    @SerializedName("starRate")                 private Integer starRate;
    @SerializedName("orderdate")                private String orderdate;
    @SerializedName("ordertime")                private String ordertime;
    @SerializedName("sendproductflag")          private String sendproductflag;
    @SerializedName("sendproductflagdetail")    private String sendproductflagdetail;
    @SerializedName("trackingno")               private String trackingno;
    @SerializedName("reviewstatus")             private String reviewstatus;
    @SerializedName("reviewMessage")            private String reviewMessage;

    protected CheckPointHistoryItemDao(Parcel in) {
        orderid = in.readString();
        proid = in.readString();
        name = in.readString();
        price = in.readString();
        pictureName = in.readString();
        picture = in.readString();
        orderdate = in.readString();
        ordertime = in.readString();
        sendproductflag = in.readString();
        sendproductflagdetail = in.readString();
        trackingno = in.readString();
        reviewstatus = in.readString();
        reviewMessage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderid);
        dest.writeString(proid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(pictureName);
        dest.writeString(picture);
        dest.writeString(orderdate);
        dest.writeString(ordertime);
        dest.writeString(sendproductflag);
        dest.writeString(sendproductflagdetail);
        dest.writeString(trackingno);
        dest.writeString(reviewstatus);
        dest.writeString(reviewMessage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckPointHistoryItemDao> CREATOR = new Creator<CheckPointHistoryItemDao>() {
        @Override
        public CheckPointHistoryItemDao createFromParcel(Parcel in) {
            return new CheckPointHistoryItemDao(in);
        }

        @Override
        public CheckPointHistoryItemDao[] newArray(int size) {
            return new CheckPointHistoryItemDao[size];
        }
    };

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

    public String getSendproductflagdetail() {
        return sendproductflagdetail;
    }

    public void setSendproductflagdetail(String sendproductflagdetail) {
        this.sendproductflagdetail = sendproductflagdetail;
    }

    public String getTrackingno() {
        return trackingno;
    }

    public void setTrackingno(String trackingno) {
        this.trackingno = trackingno;
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
