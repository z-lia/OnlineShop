package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import com.example.onlineshop.R;
import com.example.onlineshop.view.adapter.ViewPagerAdapter;
import com.example.onlineshop.viewmodel.CategoriesViewModel;

public class CategoryActivity extends AppCompatActivity {

    private CategoriesViewModel mCategoryViewModel;

    private ViewPagerAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        mCategoryViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
    }
}
