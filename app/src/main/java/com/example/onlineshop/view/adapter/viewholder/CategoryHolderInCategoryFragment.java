package com.example.onlineshop.view.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.ListItemCategoryFragmentBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.squareup.picasso.Picasso;

public class CategoryHolderInCategoryFragment extends RecyclerView.ViewHolder {
    private ListItemCategoryFragmentBinding mBinding;
    private CategoriesItem mCategoriesItem;

    public CategoryHolderInCategoryFragment(@NonNull ListItemCategoryFragmentBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        onClick();
    }
    public void bind(CategoriesItem categoriesItem) {
        mCategoriesItem = categoriesItem;
        mBinding.textViewCategoryName.setText(categoriesItem.getName());
        Picasso.get()
                .load(mCategoriesItem.getImage().getSrc())
                .into(mBinding.imageViewCategoryImage);
    }

    public void onClick(){
        mBinding.linearLayoutCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
