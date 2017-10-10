package com.gif.example.silich.vladislav.giftask;

import com.gif.example.silich.vladislav.giftask.model.GifResponse;
import com.gif.example.silich.vladislav.giftask.network.GifService;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 10.10.2017.
 */

public class TestGifSearch implements GifService {
    @Override
    public Observable<GifResponse> fetchGif(@Query("api_key") String key) {
        return null;
    }

    @Override
    public Observable<GifResponse> searchGif(@Query("q") String q, @Query("api_key") String key) {
        return null;
    }
}
