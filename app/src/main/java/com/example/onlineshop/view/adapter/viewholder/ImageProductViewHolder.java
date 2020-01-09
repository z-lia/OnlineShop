package com.example.onlineshop.view.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.ListItemProductImageBinding;
import com.squareup.picasso.Picasso;

public class ImageProductViewHolder extends RecyclerView.ViewHolder {
    private ListItemProductImageBinding mBinding;

    public ImageProductViewHolder(ListItemProductImageBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(String imageSource) {

        Picasso.get()
                .load(imageSource)
                .into(mBinding.imageViewProductSlider);

    }
}
