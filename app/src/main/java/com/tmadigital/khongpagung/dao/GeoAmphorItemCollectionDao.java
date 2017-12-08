package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoAmphorItemCollectionDao implements Parcelable {

    @SerializedName("status")       private String status;
    @SerializedName("amphorlist")   private List<GeoAmphorItemDao> amphorlist = null;

    protected GeoAmphorItemCollectionDao(Parcel in) {
        status = in.readString();
        amphorlist = in.createTypedArrayList(GeoAmphorItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(amphorlist);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoAmphorItemCollectionDao> CREATOR = new Creator<GeoAmphorItemCollectionDao>() {
        @Override
        public GeoAmphorItemCollectionDao createFromParcel(Parcel in) {
            return new GeoAmphorItemCollectionDao(in);
        }

        @Override
        public GeoAmphorItemCollectionDao[] newArray(int size) {
            return new GeoAmphorItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeoAmphorItemDao> getAmphorlist() {
        return amphorlist;
    }

    public void setAmphorlist(List<GeoAmphorItemDao> amphorlist) {
        this.amphorlist = amphorlist;
    }
}
