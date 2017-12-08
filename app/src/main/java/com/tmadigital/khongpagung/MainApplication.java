package com.tmadigital.khongpagung;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by Maximus101 on 2/24/2017 AD.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }
}
