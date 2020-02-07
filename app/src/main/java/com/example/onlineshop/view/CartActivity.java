package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;

public class CartActivity extends SingleFragmentActivityWithToolbar {

    public static Intent newIntent(Context context){
        return new Intent(context , CartActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return CartFragment.newInstance();
    }


}
