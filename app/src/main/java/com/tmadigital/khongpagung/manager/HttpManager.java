package com.tmadigital.khongpagung.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.tmadigital.khongpagung.manager.http.AddCartApiService;
import com.tmadigital.khongpagung.manager.http.ApiService;
import com.tmadigital.khongpagung.manager.http.CheckOutApiService;
import com.tmadigital.khongpagung.manager.http.CheckPointHistoryApiService;
import com.tmadigital.khongpagung.manager.http.CheckRedeemProductHistoryApiService;
import com.tmadigital.khongpagung.manager.http.GeoAmphorApiService;
import com.tmadigital.khongpagung.manager.http.GeoDistrictApiService;
import com.tmadigital.khongpagung.manager.http.GeoProvinceApiService;
import com.tmadigital.khongpagung.manager.http.LoginApiService;
import com.tmadigital.khongpagung.manager.http.ProductDetailApiService;
import com.tmadigital.khongpagung.manager.http.ProductInCartApiService;
import com.tmadigital.khongpagung.manager.http.RegisterApiService;
import com.tmadigital.khongpagung.manager.http.RemoveProductInCartApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;
    private ProductDetailApiService productDetailApiService;
    private LoginApiService loginApiService;
    private RegisterApiService registerApiService;
    private AddCartApiService addCartApiService;
    private ProductInCartApiService productInCartApiService;
    private CheckOutApiService checkOutApiService;
    private RemoveProductInCartApiService removeProductInCartApiService;
    private CheckPointHistoryApiService checkPointHistoryApiService;
    private CheckRedeemProductHistoryApiService checkRedeemProductHistoryApiService;
    private GeoProvinceApiService geoProvinceApiService;
    private GeoAmphorApiService geoAmphorApiService;
    private GeoDistrictApiService geoDistrictApiService;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.samplink.com/includes/webservice/")
                .addConverterFactory(GsonConverterFactory.create())  //----  Add After Import Converter-gson Library Dependency
                .build();

        service = retrofit.create(ApiService.class);
        productDetailApiService = retrofit.create(ProductDetailApiService.class);
        loginApiService = retrofit.create(LoginApiService.class);
        registerApiService = retrofit.create(RegisterApiService.class);
        addCartApiService = retrofit.create(AddCartApiService.class);
        productInCartApiService = retrofit.create(ProductInCartApiService.class);
        checkOutApiService = retrofit.create(CheckOutApiService.class);
        removeProductInCartApiService = retrofit.create(RemoveProductInCartApiService.class);
        checkPointHistoryApiService = retrofit.create(CheckPointHistoryApiService.class);
        checkRedeemProductHistoryApiService = retrofit.create(CheckRedeemProductHistoryApiService.class);
        geoProvinceApiService = retrofit.create(GeoProvinceApiService.class);
        geoAmphorApiService = retrofit.create(GeoAmphorApiService.class);
        geoDistrictApiService = retrofit.create(GeoDistrictApiService.class);
    }

    public ApiService getService() {
        return service;
    }

    public ProductDetailApiService getProductDetailService() { return productDetailApiService; }

    public LoginApiService getLoginApiService() { return loginApiService; }

    public RegisterApiService getRegisterApiService() {return registerApiService;}

    public AddCartApiService getAddCartApiService() {return addCartApiService;}

    public ProductInCartApiService getProductInCartApiService() {return productInCartApiService;}

    public CheckOutApiService getCheckOutApiService() {return checkOutApiService;}

    public RemoveProductInCartApiService getRemoveProductInCartApiService() {return removeProductInCartApiService;}

    public CheckPointHistoryApiService getCheckPointHistoryApiService() {return checkPointHistoryApiService;}

    public CheckRedeemProductHistoryApiService getCheckRedeemProductHistoryApiService() {return checkRedeemProductHistoryApiService;}

    public GeoProvinceApiService getGeoProvinceApiService() {return geoProvinceApiService;}

    public GeoAmphorApiService getGeoAmphorApiService() {return geoAmphorApiService;}

    public GeoDistrictApiService getGeoDistrictApiService() {return geoDistrictApiService;}
}
