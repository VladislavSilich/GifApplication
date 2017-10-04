package com.gif.example.silich.vladislav.giftask.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.app.AppController;
import com.gif.example.silich.vladislav.giftask.model.Datum;
import com.gif.example.silich.vladislav.giftask.model.GifResponse;
import com.gif.example.silich.vladislav.giftask.network.GifService;
import com.gif.example.silich.vladislav.giftask.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Lenovo on 02.10.2017.
 */

public class GifViewModel extends Observable {
     public ObservableInt progressBar;
    public ObservableInt userRecycler;
     public ObservableInt userLabel;
     public ObservableField<String> messageLabel;
    public ObservableInt edtSearch;
    public ObservableInt btnSearch;
    public ObservableInt imgGiphy;
    public ObservableField<String> edtTextSearch;
     private List<String> gifList;
     private Context context;
     private CompositeDisposable compositeDisposable = new CompositeDisposable();

     public GifViewModel(@NonNull Context context){
          this.context = context;
          this.gifList = new ArrayList<>();
         btnSearch = new ObservableInt(View.GONE);
         imgGiphy = new ObservableInt(View.VISIBLE);
         edtSearch = new ObservableInt(View.GONE);
          progressBar = new ObservableInt(View.GONE);
         userRecycler = new ObservableInt(View.GONE);
          userLabel = new ObservableInt(View.VISIBLE);
         edtTextSearch = new ObservableField<>();
          messageLabel = new ObservableField<>();
     }
     public void onClickFabToLoad(View view){
         imgGiphy.set(View.GONE);
         initializeViews();
         gifList.clear();
         fetchGifList();
     }

     public void  onClickSearchToLoad(View view){
         progressBar.set(View.VISIBLE);
         String text = edtTextSearch.get();
         String result = TextUtils.join("+",text.trim().split(" "));
         searchGifList(result);
     }

    private void searchGifList(String text) {
        AppController appControler = AppController.create(context);
        GifService gifService = appControler.getGifService();
        Disposable disposable = gifService.searchGif(text,Constant.API_KEY)
                .subscribeOn(appControler.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GifResponse>() {
                    @Override
                    public void accept(GifResponse gifResponse) throws Exception {
                        gifList.clear();
                        userLabel.set(View.GONE);
                        updateUserDataList(gifResponse.getData());
                        progressBar.set(View.GONE);
                        imgGiphy.set(View.GONE);
                    }
                },new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_message_loading_users));
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void initializeViews() {
        userLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }
    private void fetchGifList() {
        AppController appController = AppController.create(context);
        GifService gifService = appController.getGifService();
        Disposable disposable = gifService.fetchGif(Constant.API_KEY)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GifResponse>() {
                    @Override
                    public void accept(GifResponse gifResponse) throws Exception {
                        updateUserDataList(gifResponse.getData());
                        progressBar.set(View.GONE);
                        imgGiphy.set(View.GONE);
                        userLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }
                },new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_message_loading_users));
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void updateUserDataList(List<Datum> gifs) {
        if(gifs.size() != 0) {
            for (int i = 0; i < gifs.size(); i++) {
                if (gifs.get(i).getImages().getFixedHeight().getUrl() != null) {
                    gifList.add(gifs.get(i).getImages().getFixedHeight().getUrl());
                }
            }
        } else {
            messageLabel.set("Nothing to show");
            userLabel.set(View.VISIBLE);
        }
        setChanged();
        notifyObservers();
    }

    public List<String> getGifList() {
        return gifList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void showEditBtnSearch(){
        edtSearch.set(View.VISIBLE);
        btnSearch.set(View.VISIBLE);
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
