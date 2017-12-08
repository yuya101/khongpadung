package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.GeoAmphorItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/27/2017 AD.
 */

public interface GeoAmphorApiService {

    @GET("queryAmphor.php?service=showAmphor")
    Call<GeoAmphorItemCollectionDao> queryData(@Query("provinceID") String provinceID);

}
