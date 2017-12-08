package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/11/2017 AD.
 */

public class ProductDetailReviewItemDao implements Parcelable {

    @SerializedName("memberName")       private String memberName;
    @SerializedName("reviewTopic")      private String reviewTopic;
    @SerializedName("reviewDetail")     private String reviewDetail;
    @SerializedName("reviewDate")       private String reviewDate;
    @SerializedName("reviewRating")     private Integer reviewRating;
    @SerializedName("reviewstatus")       private String reviewstatus;

    protected ProductDetailReviewItemDao(Parcel in) {
        memberName = in.readString();
        reviewTopic = in.readString();
        reviewDetail = in.readString();
        reviewDate = in.readString();
        reviewstatus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(memberName);
        dest.writeString(reviewTopic);
        dest.writeString(reviewDetail);
        dest.writeString(reviewDate);
        dest.writeString(reviewstatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailReviewItemDao> CREATOR = new Creator<ProductDetailReviewItemDao>() {
        @Override
        public ProductDetailReviewItemDao createFromParcel(Parcel in) {
            return new ProductDetailReviewItemDao(in);
        }

        @Override
        public ProductDetailReviewItemDao[] newArray(int size) {
            return new ProductDetailReviewItemDao[size];
        }
    };

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getReviewTopic() {
        return reviewTopic;
    }

    public void setReviewTopic(String reviewTopic) {
        this.reviewTopic = reviewTopic;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Integer getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus;
    }
}
