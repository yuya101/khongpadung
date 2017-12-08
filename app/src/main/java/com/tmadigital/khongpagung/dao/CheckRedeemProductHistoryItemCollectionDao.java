package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public class CheckRedeemProductHistoryItemCollectionDao implements Parcelable {

    @SerializedName("status")           private String status;
    @SerializedName("productsList")     private List<CheckRedeemProductHistoryItemDao> productsList = null;

    protected CheckRedeemProductHistoryItemCollectionDao(Parcel in) {
        status = in.readString();
        productsList = in.createTypedArrayList(CheckRedeemProductHistoryItemDao.CREATOR);
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

    public static final Creator<CheckRedeemProductHistoryItemCollectionDao> CREATOR = new Creator<CheckRedeemProductHistoryItemCollectionDao>() {
        @Override
        public CheckRedeemProductHistoryItemCollectionDao createFromParcel(Parcel in) {
            return new CheckRedeemProductHistoryItemCollectionDao(in);
        }

        @Override
        public CheckRedeemProductHistoryItemCollectionDao[] newArray(int size) {
            return new CheckRedeemProductHistoryItemCollectionDao[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CheckRedeemProductHistoryItemDao> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<CheckRedeemProductHistoryItemDao> productsList) {
        this.productsList = productsList;
    }
}
