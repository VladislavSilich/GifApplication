package com.gif.example.silich.vladislav.giftask.app;

import android.app.Application;
import android.content.Context;

import com.gif.example.silich.vladislav.giftask.network.ApiFactory;
import com.gif.example.silich.vladislav.giftask.network.GifService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 02.10.2017.
 */

public class AppControler extends Application {
    private GifService gifService;
    private Scheduler scheduler;

    private static AppControler get (Context context){
        return (AppControler)context.getApplicationContext();
    }

    public static AppControler create(Context context){
        return AppControler.get(context);
    }

    public GifService getGifService(){
        if (gifService == null){
            gifService = ApiFactory.create();
        }
        return gifService;
    }
    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }
}

