package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;

public class RegisterActivity extends SingleFragmentActivityWithToolbar {

    @Override
    public Fragment createFragment() {
        return RegisterFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context ,RegisterActivity.class);
        return intent;
    }

}





