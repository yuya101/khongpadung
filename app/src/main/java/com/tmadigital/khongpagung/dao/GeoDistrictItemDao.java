package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public class GeoDistrictItemDao implements Parcelable {

    @SerializedName("districtid")       private String districtid;
    @SerializedName("districtname")     private String districtname;

    protected GeoDistrictItemDao(Parcel in) {
        districtid = in.readString();
        districtname = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(districtid);
        dest.writeString(districtname);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeoDistrictItemDao> CREATOR = new Creator<GeoDistrictItemDao>() {
        @Override
        public GeoDistrictItemDao createFromParcel(Parcel in) {
            return new GeoDistrictItemDao(in);
        }

        @Override
        public GeoDistrictItemDao[] newArray(int size) {
            return new GeoDistrictItemDao[size];
        }
    };

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }
}
