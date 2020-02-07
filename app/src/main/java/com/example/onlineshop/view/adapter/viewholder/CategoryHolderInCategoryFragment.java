package com.example.onlineshop.view.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.databinding.ListItemCategoryFragmentBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.ProductDetailActivity;
import com.example.onlineshop.view.ProductsOfCategoryActivity;
import com.squareup.picasso.Picasso;

public class CategoryHolderInCategoryFragment extends RecyclerView.ViewHolder {
    private ListItemCategoryFragmentBinding mBinding;
    private CategoriesItem mCategoriesItem;
    private Context mContext;

    public CategoryHolderInCategoryFragment(@NonNull ListItemCategoryFragmentBinding binding , Context context) {
        super(binding.getRoot());
        mBinding = binding;
        mContext = context;
        onClick();
    }
    public void bind(CategoriesItem categoriesItem) {
        mCategoriesItem = categoriesItem;
        mBinding.textViewCategoryName.setText(categoriesItem.getName());
        Picasso.get()
                .load(mCategoriesItem.getImage().getSrc())
                .into(mBinding.imageViewCategoryImage);
    }

    private void onClick(){
        mBinding.linearLayoutCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProductsOfCategoryActivity.newIntent(mContext , mCategoriesItem.getId() , mCategoriesItem.getName());
                mContext.startActivity(intent);

            }
        });
    }
}
