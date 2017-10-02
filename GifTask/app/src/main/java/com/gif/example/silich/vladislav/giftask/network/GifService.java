package com.gif.example.silich.vladislav.giftask.network;

import com.gif.example.silich.vladislav.giftask.model.GifResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 29.09.2017.
 */

public interface GifService {
    @GET("trending")
    Observable<GifResponse> fetchGif(@Query("api_key")String key);
}
