package com.example.onlineshop.view;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ProductsOfCategoryActivity extends SingleFragmentActivity {
    private static final String EXTRA_CATEGORY_ID = "com.example.onlineshop.view-categoryID";
    private static final String EXTRA_CATEGORY_NAME = "com.example.onlineshop.view-categoryName";
    private int mCategoryId;
    private String mCategoryName;

    @Override
    public Fragment createFragment() {
        mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        mCategoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        return ProductCategoriesFragment.newInstance(mCategoryId , mCategoryName);
    }

    public static Intent newIntent(Context context , int categoryId , String categoryName){
        Intent intent = new Intent(context ,ProductsOfCategoryActivity.class );
        intent.putExtra(EXTRA_CATEGORY_ID , categoryId);
        intent.putExtra(EXTRA_CATEGORY_NAME , categoryName);
        return intent;
    }
}
