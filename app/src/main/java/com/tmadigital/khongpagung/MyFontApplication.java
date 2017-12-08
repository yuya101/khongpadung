package com.tmadigital.khongpagung;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Maximus on 8/1/2017 AD.
 */

public class MyFontApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
        initFont();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Kanit-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
