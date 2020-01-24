package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListItemCategoryBinding;
import com.example.onlineshop.databinding.ListItemCategoryFragmentBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.HomeMainActivity;
import com.example.onlineshop.view.adapter.viewholder.CategoryHolder;
import com.example.onlineshop.view.adapter.viewholder.CategoryHolderInCategoryFragment;

import java.util.List;

public class CategoryFragmentRecyclerAdapter extends RecyclerView.Adapter<CategoryHolderInCategoryFragment> {

    private List<CategoriesItem> mCategoryItems;
    private Context mContext;

    public CategoryFragmentRecyclerAdapter(List<CategoriesItem> categoryItems, Context context) {
        this.mCategoryItems = categoryItems;
        this.mContext = context;
    }

    public void setCategoryItems(List<CategoriesItem> categoryItems) {
        this.mCategoryItems = categoryItems;
    }

    @NonNull
    @Override
    public CategoryHolderInCategoryFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemCategoryFragmentBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(mContext), R.layout.list_item_category_fragment, parent, false);
            return new CategoryHolderInCategoryFragment(binding , mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolderInCategoryFragment holder, int position) {
        holder.bind(mCategoryItems.get(position));
    }


    @Override
    public int getItemCount() {
        return mCategoryItems.size();
    }

}
