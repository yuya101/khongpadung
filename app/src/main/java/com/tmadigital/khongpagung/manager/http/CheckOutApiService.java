package com.tmadigital.khongpagung.manager.http;

import com.tmadigital.khongpagung.dao.CheckOutItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maximus101 on 4/19/2017 AD.
 */

public interface CheckOutApiService {

    @GET("checkOutProcess.php?service=checkOut")
    Call<CheckOutItemDao> checkOutAddData(@Query("memberID") String memberID,
                                          @Query("name") String name,
                                          @Query("lastname") String lastname,
                                          @Query("address") String address,
                                          @Query("soi") String soi,
                                          @Query("street") String street,
                                          @Query("tumbon") String district,
                                          @Query("amphor") String amphor,
                                          @Query("province") String province,
                                          @Query("postcode") String postcode,
                                          @Query("comment") String comment);

}
