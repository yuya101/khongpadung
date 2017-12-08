package com.tmadigital.khongpagung.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus101 on 4/21/2017 AD.
 */

public class RemoveProductInCartItemDao {
    @SerializedName("status")       private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
