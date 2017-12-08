package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public class CheckPointHistoryItemCollectionDao implements Parcelable {
    @SerializedName("status")           private String status;
    @SerializedName("productsList")     private List<CheckPointHistoryItemDao> productsList = null;

    protected CheckPointHistoryItemCollectionDao(Parcel in) {
        status = in.readString();
        productsList = in.createTypedArrayList(CheckPointHistoryItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(productsList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckPointHistoryItemCollectionDao> CREATOR = new Creator<CheckPointHistoryItemCollectionDao>() {
        @Override
        public CheckPointHistoryItemCollectionDao createFromParcel(Parcel in) {
            return new CheckPointHistoryItemCollectionDao(in);
        }

        @Override
        public CheckPointHistoryItemCollectionDao[] newArray(int size) {
            return new CheckPointHistoryItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CheckPointHistoryItemDao> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<CheckPointHistoryItemDao> productsList) {
        this.productsList = productsList;
    }
}
