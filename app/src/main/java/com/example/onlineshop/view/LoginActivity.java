package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends SingleFragmentActivityWithToolbar {

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context ,LoginActivity.class);
        return intent;
    }

}
