package com.tmadigital.khongpagung.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maximus101 on 4/10/2017 AD.
 */

public class ProductNearGoneItemCollectionDao {
    @SerializedName("status") private String status;
    @SerializedName("products") private List<ProductNearGoneItemDao> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductNearGoneItemDao> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNearGoneItemDao> products) {
        this.products = products;
    }
}
