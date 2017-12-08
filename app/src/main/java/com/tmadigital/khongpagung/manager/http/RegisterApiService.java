package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.RegisterItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/15/2017 AD.
 */

public interface RegisterApiService {

    @GET("registerServiceAndroid.php")
    Call<RegisterItemCollectionDao> registerData(@Query("name") String name, @Query("lastname") String lastname,
                                              @Query("email") String email, @Query("password") String password);

}
