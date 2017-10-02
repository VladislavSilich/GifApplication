package com.gif.example.silich.vladislav.giftask.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.databinding.ActivityMainBinding;
import com.gif.example.silich.vladislav.giftask.viewModel.GifViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{
    ActivityMainBinding mainActivityBinding;
    private GifViewModel gifViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setUpObserver(gifViewModel);
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gifViewModel = new GifViewModel(this);
        mainActivityBinding.setModel(gifViewModel);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

}
