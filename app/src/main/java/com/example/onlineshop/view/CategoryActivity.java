package com.example.onlineshop.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;


import com.example.onlineshop.R;
import com.example.onlineshop.view.adapter.CategoryPagerAdapter;
import com.example.onlineshop.viewmodel.CategoriesViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CategoryActivity extends AppCompatActivity {

    private CategoriesViewModel mCategoryViewModel;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    //    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mCategoryViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);

        mCategoryViewModel.getSubCategoriesLiveData2().getValue();
        mViewPager2 = findViewById(R.id.view_pager2_category);
        mTabLayout = findViewById(R.id.tab_layout_category);

        mCategoryPagerAdapter = new CategoryPagerAdapter(this);
        mViewPager2.setAdapter(mCategoryPagerAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        //tab.setText("Tab " + (position + 1));
                        tab.setText(mCategoryViewModel.getParentCategoriesListLiveData().getValue().get (position).getName()+"");
                    }
                }).attach();
    }

    public static Intent newIntent(Context context){
        return new Intent(context , CategoryActivity.class);
    }

}
