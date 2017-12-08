package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoDistrictItemCollectionDao implements Parcelable {

    @SerializedName("status")           private String status;
    @SerializedName("tumbonlist")       private List<GeoDistrictItemDao> tumbonlist = null;

    protected GeoDistrictItemCollectionDao(Parcel in) {
        status = in.readString();
        tumbonlist = in.createTypedArrayList(GeoDistrictItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(tumbonlist);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoDistrictItemCollectionDao> CREATOR = new Creator<GeoDistrictItemCollectionDao>() {
        @Override
        public GeoDistrictItemCollectionDao createFromParcel(Parcel in) {
            return new GeoDistrictItemCollectionDao(in);
        }

        @Override
        public GeoDistrictItemCollectionDao[] newArray(int size) {
            return new GeoDistrictItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeoDistrictItemDao> getTumbonlist() {
        return tumbonlist;
    }

    public void setTumbonlist(List<GeoDistrictItemDao> tumbonlist) {
        this.tumbonlist = tumbonlist;
    }
}
