package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.example.onlineshop.R;
import com.example.onlineshop.network.WooCommerceRepository;
import com.example.onlineshop.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private SplashViewModel mSplashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        mSplashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        new PreFetchDataFromServer().execute(mSplashViewModel);

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(SplashActivity.this, HomeMainActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);


    }

    private class PreFetchDataFromServer extends AsyncTask <SplashViewModel,Void,Void>{

        @Override
        protected Void doInBackground(SplashViewModel... splashViewModels) {
            splashViewModels[0].fetchProducts();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Home-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, HomeMainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                finish();
            }
            }, SPLASH_DISPLAY_LENGTH);

        }
    }
}
