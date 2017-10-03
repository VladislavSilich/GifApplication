package com.gif.example.silich.vladislav.giftask.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.databinding.ItemGifBinding;
import com.gif.example.silich.vladislav.giftask.viewModel.ItemGifViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lenovo on 03.10.2017.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifAdapterViewHolder> {

    private List<String> urlList;

    public GifAdapter(){
        this.urlList = Collections.emptyList();
    }

    @Override
    public GifAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGifBinding itemGifBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gif ,parent, false);
        return new GifAdapterViewHolder(itemGifBinding);
    }

    @Override
    public void onBindViewHolder(GifAdapterViewHolder holder, int position) {
        holder.bindGif(urlList.get(position));
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public void setGifList(List<String> gifList) {
        this.urlList = gifList;
        notifyDataSetChanged();
    }

    public class GifAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemGifBinding mItemGifBinding;
        public GifAdapterViewHolder(ItemGifBinding itemGifBinding) {
            super(itemGifBinding.itemGif);
            this.mItemGifBinding = itemGifBinding;
        }

        void bindGif(String url){
            if (mItemGifBinding.getGifViewModel() == null){
                mItemGifBinding.setGifViewModel(new ItemGifViewModel(url));
            }else{
                mItemGifBinding.getGifViewModel().setUrl(url);
            }
        }
    }
}
