package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/12/2017 AD.
 */

public class LoginItemCollectionDao implements Parcelable {

    @SerializedName("status")       private String status;
    @SerializedName("memberinfo")   private List<LoginItemDao> memberinfo = null;

    protected LoginItemCollectionDao(Parcel in) {
        status = in.readString();
        memberinfo = in.createTypedArrayList(LoginItemDao.CREATOR);
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

    public static final Creator<LoginItemCollectionDao> CREATOR = new Creator<LoginItemCollectionDao>() {
        @Override
        public LoginItemCollectionDao createFromParcel(Parcel in) {
            return new LoginItemCollectionDao(in);
        }

        @Override
        public LoginItemCollectionDao[] newArray(int size) {
            return new LoginItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoginItemDao> getMemberinfo() {
        return memberinfo;
    }

    public void setMemberinfo(List<LoginItemDao> memberinfo) {
        this.memberinfo = memberinfo;
    }

}
