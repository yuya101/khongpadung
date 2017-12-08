package com.tmadigital.khongpagung.manager.http;

//import com.tmadigital.samplink.dao.ProductItemDao;

import com.tmadigital.khongpagung.dao.ProductAllItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 3/2/2017 AD.
 */

public interface ApiService {

    @GET("queryProductAndroid.php?service=productAll&orderBy=adddate&orderType=desc")
    Call<ProductAllItemCollectionDao> loadProductList(@Query("selectProduct") String selectProduct,
                                                      @Query("productNameSearch") String productNameSearch);

}
