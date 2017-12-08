package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.ProductInCartItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/17/2017 AD.
 */

public interface ProductInCartApiService {

    @GET("queryProductInCartAndroid.php?service=productInCart")
    Call<ProductInCartItemCollectionDao> loadProductInCartData(@Query("memberID") String memberID);

}
