package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.CheckRedeemProductHistoryItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/22/2017 AD.
 */

public interface CheckRedeemProductHistoryApiService {

    @GET("queryProductHistoryAndroid.php?service=productHistory")
    Call<CheckRedeemProductHistoryItemCollectionDao> loadHistoryData(@Query("memberID") String memberID);

}
