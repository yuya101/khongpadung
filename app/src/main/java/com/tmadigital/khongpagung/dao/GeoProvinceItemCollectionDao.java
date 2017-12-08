package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoProvinceItemCollectionDao implements Parcelable {

    @SerializedName("status")           private String status;
    @SerializedName("provincelist")     private List<GeoProvinceItemDao> provincelist = null;

    protected GeoProvinceItemCollectionDao(Parcel in) {
        status = in.readString();
        provincelist = in.createTypedArrayList(GeoProvinceItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(provincelist);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoProvinceItemCollectionDao> CREATOR = new Creator<GeoProvinceItemCollectionDao>() {
        @Override
        public GeoProvinceItemCollectionDao createFromParcel(Parcel in) {
            return new GeoProvinceItemCollectionDao(in);
        }

        @Override
        public GeoProvinceItemCollectionDao[] newArray(int size) {
            return new GeoProvinceItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeoProvinceItemDao> getProvincelist() {
        return provincelist;
    }

    public void setProvincelist(List<GeoProvinceItemDao> provincelist) {
        this.provincelist = provincelist;
    }
}
