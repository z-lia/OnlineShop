package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentHomeBinding;
import com.example.onlineshop.databinding.ListItemCategoryBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.adapter.viewholder.CategoryHolder;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private List<CategoriesItem> mCategoryItems;
    private Context mContext;

    public CategoryRecyclerAdapter(List<CategoriesItem> categoryItems, Context context) {
        this.mCategoryItems = categoryItems;
        this.mContext = context;
    }

    public void setCategoryItems(List<CategoriesItem> categoryItems) {
        this.mCategoryItems = categoryItems;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.list_item_category ,parent ,false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(mCategoryItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoryItems.size();
    }
}
