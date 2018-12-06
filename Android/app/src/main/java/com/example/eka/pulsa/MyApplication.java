package com.example.eka.pulsa;


import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

/**
 * Created by Firman on 12/6/2018.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(this);
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
