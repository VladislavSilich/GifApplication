package com.gif.example.silich.vladislav.giftask.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Lenovo on 03.10.2017.
 */

public class ItemGifViewModel extends BaseObservable {

    private String gifUrl;

    public ItemGifViewModel(String gifUrl){
        this.gifUrl = gifUrl;
    }

    public String getProfileThumb() {
        return gifUrl;
    }
    @BindingAdapter("imageUrl")public static void setImageUrl(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).asGif().into(imageView);
    }

    public void setUrl(String url){
        this.gifUrl = url;
        notifyChange();
    }
}
