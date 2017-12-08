package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.LoginItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/12/2017 AD.
 */

public interface LoginApiService {

    @GET("loginServiceAndroid.php?service=login")
    Call<LoginItemCollectionDao> loginData(@Query("username") String userName, @Query("password") String password);

}
