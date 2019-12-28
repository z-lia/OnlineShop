package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.WooCommerceRepository;

public class SplashViewModel extends AndroidViewModel {
    private WooCommerceRepository mRepository;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
    }

    public void fetchProducts(){
        mRepository.fetchSpecificProducts();
    }
}
