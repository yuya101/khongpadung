package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.ProductDetailItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/11/2017 AD.
 */

public interface ProductDetailApiService {

    @GET("queryProductDetailAndroid.php?service=productDetail&orderBy=productName&orderType=desc")
    Call<ProductDetailItemCollectionDao> loadProductDetailData(@Query("proID") String productID);

}
