package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/15/2017 AD.
 */

public class RegisterItemCollectionDao implements Parcelable {

    @SerializedName("status")       private String status;
    @SerializedName("memberinfo")   private List<RegisterItemDao> memberinfo = null;

    protected RegisterItemCollectionDao(Parcel in) {
        status = in.readString();
        memberinfo = in.createTypedArrayList(RegisterItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(memberinfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterItemCollectionDao> CREATOR = new Creator<RegisterItemCollectionDao>() {
        @Override
        public RegisterItemCollectionDao createFromParcel(Parcel in) {
            return new RegisterItemCollectionDao(in);
        }

        @Override
        public RegisterItemCollectionDao[] newArray(int size) {
            return new RegisterItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RegisterItemDao> getMemberinfo() {
        return memberinfo;
    }

    public void setMemberinfo(List<RegisterItemDao> memberinfo) {
        this.memberinfo = memberinfo;
    }
}
