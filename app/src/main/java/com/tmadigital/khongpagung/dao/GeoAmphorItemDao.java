package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoAmphorItemDao implements Parcelable {

    @SerializedName("amphorid")     private String amphorid;
    @SerializedName("amphorname")   private String amphorname;

    protected GeoAmphorItemDao(Parcel in) {
        amphorid = in.readString();
        amphorname = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amphorid);
        dest.writeString(amphorname);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoAmphorItemDao> CREATOR = new Creator<GeoAmphorItemDao>() {
        @Override
        public GeoAmphorItemDao createFromParcel(Parcel in) {
            return new GeoAmphorItemDao(in);
        }

        @Override
        public GeoAmphorItemDao[] newArray(int size) {
            return new GeoAmphorItemDao[size];
        }
    };

    public String getAmphorid() {
        return amphorid;
    }

    public void setAmphorid(String amphorid) {
        this.amphorid = amphorid;
    }

    public String getAmphorname() {
        return amphorname;
    }

    public void setAmphorname(String amphorname) {
        this.amphorname = amphorname;
    }
}
