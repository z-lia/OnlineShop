package com.example.onlineshop.view.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.ListItemCategoryBinding;
import com.example.onlineshop.model.CategoriesItem;

public class CategoryHolder extends RecyclerView.ViewHolder {
    private ListItemCategoryBinding mBinding;

    public CategoryHolder(ListItemCategoryBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        onClick();
    }

    public void bind(CategoriesItem categoriesItem) {
        mBinding.textViewCategory.setText(categoriesItem.getName());
    }

    public void onClick(){
        mBinding.textViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
