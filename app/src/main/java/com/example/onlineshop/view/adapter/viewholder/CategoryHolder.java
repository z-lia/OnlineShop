package com.example.onlineshop.view.adapter.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.ListItemCategoryBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.ProductsOfCategoryActivity;

public class CategoryHolder extends RecyclerView.ViewHolder {
    private ListItemCategoryBinding mBinding;
    private Context mContext;
    private CategoriesItem mCategoryItem;

    public CategoryHolder(ListItemCategoryBinding binding , Context context) {
        super(binding.getRoot());
        mBinding = binding;
        mContext = context;
        onClick();
    }

    public void bind(CategoriesItem categoriesItem) {
        mCategoryItem = categoriesItem;
        mBinding.textViewCategory.setText(categoriesItem.getName());
    }

    public void onClick(){
        mBinding.textViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(ProductsOfCategoryActivity.newIntent(mContext , mCategoryItem.getId() , mCategoryItem.getName()));
            }
        });
    }
}
