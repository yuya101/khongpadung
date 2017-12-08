package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/19/2017 AD.
 */

public class CheckOutItemDao implements Parcelable {
    @SerializedName("status")       private String status;
    @SerializedName("Message")   private String message;
    @SerializedName("point")   private String point;
    @SerializedName("pointShow")   private String pointShow;

    protected CheckOutItemDao(Parcel in) {
        status = in.readString();
        message = in.readString();
        point = in.readString();
        pointShow = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(point);
        dest.writeString(pointShow);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckOutItemDao> CREATOR = new Creator<CheckOutItemDao>() {
        @Override
        public CheckOutItemDao createFromParcel(Parcel in) {
            return new CheckOutItemDao(in);
        }

        @Override
        public CheckOutItemDao[] newArray(int size) {
            return new CheckOutItemDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointShow() {
        return pointShow;
    }

    public void setPointShow(String pointShow) {
        this.pointShow = pointShow;
    }
}
