package com.example.onlineshop.view;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.onlineshop.R;

public class ProductDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }
}
