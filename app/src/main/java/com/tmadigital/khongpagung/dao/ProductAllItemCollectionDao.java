package com.tmadigital.khongpagung.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/10/2017 AD.
 */

public class ProductAllItemCollectionDao {
    @SerializedName("status") private String status;
    @SerializedName("data") private List<ProductAllItemDao> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductAllItemDao> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAllItemDao> products) {
        this.products = products;
    }
}
