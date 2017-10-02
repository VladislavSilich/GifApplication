package com.gif.example.silich.vladislav.giftask.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gif.example.silich.vladislav.giftask.utils.Constant.BASE_URL;

/**
 * Created by Lenovo on 02.10.2017.
 */

public class ApiFactory {
    public static GifService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GifService.class);
    }

}
