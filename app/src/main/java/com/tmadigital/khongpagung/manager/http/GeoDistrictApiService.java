package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.GeoDistrictItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public interface GeoDistrictApiService {

    @GET("queryTumbon.php?service=showTumbon")
    Call<GeoDistrictItemCollectionDao> queryData(@Query("amphorID") String amphorID);

}
