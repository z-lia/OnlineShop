package com.example.onlineshop.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;

public class NoInternetActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context , NoInternetActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return NoInternetFragment.newInstance();
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_no_internet);
//    }


}
