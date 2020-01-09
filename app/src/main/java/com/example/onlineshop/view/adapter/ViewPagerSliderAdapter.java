package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListItemProductImageBinding;
import com.example.onlineshop.view.adapter.viewholder.ImageProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerSliderAdapter extends RecyclerView.Adapter<ImageProductViewHolder> {
    private List <String> mImageSources;
    private Context mContext;

    public  ViewPagerSliderAdapter(ArrayList<String> images , Context context) {
        mImageSources = images;
        mContext= context;
    }

    @NonNull
    @Override
    public ImageProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemProductImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.list_item_product_image ,parent ,false);
        return new ImageProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageProductViewHolder holder, int position) {
        holder.bind(mImageSources.get(position));

    }

    @Override
    public int getItemCount() {
        return mImageSources.size();
    }
}
