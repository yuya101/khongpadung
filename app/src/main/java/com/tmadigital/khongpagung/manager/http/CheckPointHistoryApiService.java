package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.CheckPointHistoryItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public interface CheckPointHistoryApiService {

    @GET("queryPointHistoryAndroid.php?service=pointHistory")
    Call<CheckPointHistoryItemCollectionDao> loadHistoryData(@Query("memberID") String memberID);

}
