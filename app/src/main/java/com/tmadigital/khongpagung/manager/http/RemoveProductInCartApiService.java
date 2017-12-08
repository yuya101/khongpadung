package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.RemoveProductInCartItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/21/2017 AD.
 */

public interface RemoveProductInCartApiService {

    @GET("removeProductFromCart.php?service=removeProduct")
    Call<RemoveProductInCartItemDao> removeData(@Query("memberID") String memberID, @Query("productID") String productID);

}
