package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private WooCommerceRepository mRepository;
    private MutableLiveData<List<Product>> mSearchProducts;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
        mSearchProducts = new MutableLiveData<>();
    }

    public void searchProduct(String searchQuery){
        mSearchProducts = mRepository.fetchSearchProducts(searchQuery);
    }


    public MutableLiveData<List<Product>> getSearchProducts() {
        return mSearchProducts;
    }
}
