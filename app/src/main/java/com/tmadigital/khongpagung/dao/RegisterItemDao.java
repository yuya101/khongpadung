package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/15/2017 AD.
 */

public class RegisterItemDao implements Parcelable {
    @SerializedName("bname")        private String bname;
    @SerializedName("blastname")    private String blastname;
    @SerializedName("gender")       private String gender;
    @SerializedName("bmobile")      private String bmobile;
    @SerializedName("point")        private String point;
    @SerializedName("pointShow")    private String pointShow;
    @SerializedName("memberID")     private String memberID;
    @SerializedName("token")        private String token;

    protected RegisterItemDao(Parcel in) {
        bname = in.readString();
        blastname = in.readString();
        gender = in.readString();
        bmobile = in.readString();
        point = in.readString();
        pointShow = in.readString();
        memberID = in.readString();
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bname);
        dest.writeString(blastname);
        dest.writeString(gender);
        dest.writeString(bmobile);
        dest.writeString(point);
        dest.writeString(pointShow);
        dest.writeString(memberID);
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterItemDao> CREATOR = new Creator<RegisterItemDao>() {
        @Override
        public RegisterItemDao createFromParcel(Parcel in) {
            return new RegisterItemDao(in);
        }

        @Override
        public RegisterItemDao[] newArray(int size) {
            return new RegisterItemDao[size];
        }
    };

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBlastname() {
        return blastname;
    }

    public void setBlastname(String blastname) {
        this.blastname = blastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBmobile() {
        return bmobile;
    }

    public void setBmobile(String bmobile) {
        this.bmobile = bmobile;
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

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
