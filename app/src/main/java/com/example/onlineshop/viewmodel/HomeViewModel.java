package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData <List<Product>> mLatestProductLiveData;
    private MutableLiveData <List<Product>> mBestProductsLiveData;
    private MutableLiveData <List<Product>> mMostPopularProductsLiveData;

    private WooCommerceRepository mRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        mRepository = WooCommerceRepository.getsInstance();
        mLatestProductLiveData = mRepository.getLatestProductLiveData();
        mBestProductsLiveData = mRepository.getBestProductsLiveData();
        mMostPopularProductsLiveData = mRepository.getMostPopularProductsLiveData();
    }

    public MutableLiveData<List<Product>> getLatestProductLiveData() {
        return mLatestProductLiveData;
    }

    public MutableLiveData<List<Product>> getBestProductsLiveData() {
        return mBestProductsLiveData;
    }

    public MutableLiveData<List<Product>> getMostPopularProductsLiveData() {
        return mMostPopularProductsLiveData;
    }
}
