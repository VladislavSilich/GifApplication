package com.gif.example.silich.vladislav.giftask.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.adapter.GifAdapter;
import com.gif.example.silich.vladislav.giftask.databinding.ActivityMainBinding;
import com.gif.example.silich.vladislav.giftask.viewModel.GifViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{
    private  ActivityMainBinding mainActivityBinding;
    private GifViewModel gifViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setUpListOfGifView(mainActivityBinding.listGif);
        setUpObserver(gifViewModel);
    }
    private void setUpListOfGifView(RecyclerView listGif) {
        GifAdapter gifAdapter = new GifAdapter();
        listGif.setAdapter(gifAdapter);
        listGif.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gifViewModel = new GifViewModel(this);
        mainActivityBinding.setModel(gifViewModel);
    }
    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  GifViewModel) {
            GifAdapter gifAdapter = (GifAdapter) mainActivityBinding.listGif.getAdapter();
            GifViewModel gifViewModel = (GifViewModel) o;
            gifAdapter.setGifList(gifViewModel.getGifList());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        gifViewModel.reset();
    }
}
