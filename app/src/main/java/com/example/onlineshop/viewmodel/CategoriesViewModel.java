package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {

    private WooCommerceRepository mRepository;

    private LiveData<List<CategoriesItem>> categoriesListLiveData;
    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
        mRepository.fetchAllCategories();
        categoriesListLiveData = mRepository.getCategoryItemsLiveData();
    }

    public LiveData<List<CategoriesItem>> getCategoriesListLiveData() {
        return categoriesListLiveData;
    }
}
