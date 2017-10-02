package com.gif.example.silich.vladislav.giftask.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Lenovo on 02.10.2017.
 */

public class ItemGifViewModel extends BaseObservable {
    private String url;
    private Context context;

    public ItemGifViewModel(String url,Context context){
        this.url = url;
        this.context = context;
    }

    public  String getProfileThumb() {
        return url;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
      Glide.with(imageView.getContext())
               .load(url)
               .into(imageView);
    }

    public void setUrl(String url){
        this.url = url;
        notifyChange();
    }
}
