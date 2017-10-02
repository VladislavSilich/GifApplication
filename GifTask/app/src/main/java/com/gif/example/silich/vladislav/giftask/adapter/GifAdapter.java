package com.gif.example.silich.vladislav.giftask.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gif.example.silich.vladislav.giftask.R;
import com.gif.example.silich.vladislav.giftask.databinding.RecyclerViewItemBinding;
import com.gif.example.silich.vladislav.giftask.viewModel.ItemGifViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lenovo on 02.10.2017.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifAdapterViewHolder>{

    private List<String> urlGif;

    public GifAdapter(){
        this.urlGif = Collections.emptyList();
    }

    @Override
    public GifAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewItemBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_view_item ,parent, false);
        return new GifAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(GifAdapterViewHolder holder, int position) {
        holder.bindGif(urlGif.get(position));
    }

    @Override
    public int getItemCount() {
        return urlGif.size();
    }

    public void setUrlList(List<String > urlList){
        this.urlGif = urlList;
        notifyDataSetChanged();
    }

    public static class GifAdapterViewHolder extends RecyclerView.ViewHolder{
        RecyclerViewItemBinding recyclerViewItemBinding;
        public GifAdapterViewHolder(RecyclerViewItemBinding itemBinding) {
            super(itemBinding.itemGif);
            this.recyclerViewItemBinding = itemBinding;
        }

        void bindGif(String url){
           if ( recyclerViewItemBinding.getGifViewModel() == null ){
               recyclerViewItemBinding.setGifViewModel(new ItemGifViewModel(url,itemView.getContext()));
           }else {
               recyclerViewItemBinding.getGifViewModel().setUrl(url);
           }
        }
    }

}
