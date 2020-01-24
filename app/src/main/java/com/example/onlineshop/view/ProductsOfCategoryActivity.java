package com.example.onlineshop.view;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ProductsOfCategoryActivity extends SingleFragmetActivityWithToolbar {
    private static final String EXTRA_CATEGORY_ID = "com.example.onlineshop.view-categoryID";
    private int mCategoryId;

    @Override
    public Fragment createFragment() {
        //getIntent().getIntExtra(EXTRA_CATEGORY_ID);
        return ProductCategoriesFragment.newInstance(mCategoryId);
    }

    public static Intent newIntent(Context context , int categoryId){
        Intent intent = new Intent(context ,ProductsOfCategoryActivity.class );
        intent.putExtra(EXTRA_CATEGORY_ID , categoryId);
        return intent;
    }
}
