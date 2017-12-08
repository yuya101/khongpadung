package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoProvinceItemDao implements Parcelable {

    @SerializedName("provinceid")       private String provinceid;
    @SerializedName("provincename")     private String provincename;

    protected GeoProvinceItemDao(Parcel in) {
        provinceid = in.readString();
        provincename = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(provinceid);
        dest.writeString(provincename);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoProvinceItemDao> CREATOR = new Creator<GeoProvinceItemDao>() {
        @Override
        public GeoProvinceItemDao createFromParcel(Parcel in) {
            return new GeoProvinceItemDao(in);
        }

        @Override
        public GeoProvinceItemDao[] newArray(int size) {
            return new GeoProvinceItemDao[size];
        }
    };

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }
}
