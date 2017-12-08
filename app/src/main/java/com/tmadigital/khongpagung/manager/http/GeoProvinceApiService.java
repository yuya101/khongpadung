package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.GeoProvinceItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public interface GeoProvinceApiService {

    @GET("queryProvince.php?service=showProvince")
    Call<GeoProvinceItemCollectionDao> queryData();

}
