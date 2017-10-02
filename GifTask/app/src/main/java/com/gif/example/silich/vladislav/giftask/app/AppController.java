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

public class AppController extends Application {
    private GifService gifService;
    private Scheduler scheduler;

    private static AppController get (Context context){
        return (AppController)context.getApplicationContext();
    }

    public static AppController create(Context context){
        return AppController.get(context);
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

