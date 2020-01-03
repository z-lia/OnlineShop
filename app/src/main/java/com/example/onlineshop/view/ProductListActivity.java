package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;

public class ProductListActivity extends SingleFragmentActivity {
    public static final String LATEST_PRODUCT ="latest_product";
    public static final String MOST_VISIT_PRODUCT ="most_visit_product";
    public static final String POPULAR_PRODUCT ="popular_product";

    @Override
    public Fragment createFragment() {
        return null;
    }


    public static Intent newIntent(Context context , String productListType){
        Intent intent = new Intent(context, ProductListActivity.class);
        return intent;
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_list);
//    }


}
