package com.example.onlineshop.view;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;

public class ProductDetailActivity extends SingleFragmentActivity {

    private static final String EXTRA_PRODUCT_ID ="com.example.onlineshop.product_id" ;

    @Override
    public Fragment createFragment() {
        int productId = getIntent().getIntExtra(EXTRA_PRODUCT_ID , 0);
        return ProductDetailFragment.newInstance(productId);
    }

    public static Intent newIntent(Context context , int productId){
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        return intent;
    }

}
