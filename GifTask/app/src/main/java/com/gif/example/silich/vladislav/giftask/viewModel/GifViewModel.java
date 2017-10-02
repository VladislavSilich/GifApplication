package com.gif.example.silich.vladislav.giftask.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.app.AppController;
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

     private List<GifResponse> gifList;
     private Context context;
     private CompositeDisposable compositeDisposable = new CompositeDisposable();

     public GifViewModel(@NonNull Context context){
          this.context = context;
          this.gifList = new ArrayList<>();
          progressBar = new ObservableInt(View.GONE);
          userRecycler = new ObservableInt(View.GONE);
          userLabel = new ObservableInt(View.VISIBLE);
          messageLabel = new ObservableField<>("Press to button to load people");
     }

     public void onClickFabToLoad(View view){
         initializeViews();
         fetchUsersList();
     }


    public void initializeViews() {
        userLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }
    private void fetchUsersList() {
        AppController appController = AppController.create(context);
        GifService gifService = appController.getGifService();

        Disposable disposable = gifService.fetchGif(Constant.API_KEY)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GifResponse>() {
                    @Override
                    public void accept(GifResponse gifResponse) throws Exception {
                        gifResponse.getMeta().getStatus();
                        progressBar.set(View.GONE);
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

    private void updateUserDataList(List<GifResponse> gifs) {
        gifList.addAll(gifs);
        setChanged();
        notifyObservers();
    }

    public List<GifResponse> getUserList() {
        return gifList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
