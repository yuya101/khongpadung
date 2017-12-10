package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.ProductDetailItemCollectionDao;
import com.tmadigital.khongpagung.dao.ProductDetailNewCollectionItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/11/2017 AD.
 */

public interface ProductDetailNewApiService {

    @GET("queryProductDetail.php?service=productDetail")
    Call<ProductDetailNewCollectionItemDao> loadProductDetailData(@Query("proID") String productID);

}
