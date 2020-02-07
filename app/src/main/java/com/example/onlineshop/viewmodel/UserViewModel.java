package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.WooCommerceRepository;

public class UserViewModel extends AndroidViewModel {
    private WooCommerceRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
    }

    public void register(String username , String password){
        mRepository.register(username , password);
    }
}
