package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.AddCartItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/16/2017 AD.
 */

public interface AddCartApiService {

    @GET("addProductToCart.php?service=addProductToCart")
    Call<AddCartItemDao> updateAddCartData(@Query("memberID") String memberID, @Query("productID") String productID);

}
