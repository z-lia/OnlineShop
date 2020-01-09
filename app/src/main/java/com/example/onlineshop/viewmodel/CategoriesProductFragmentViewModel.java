package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.List;

public class CategoriesProductFragmentViewModel extends AndroidViewModel {
    private WooCommerceRepository mRepository;
    private LiveData<List<Product>> mProductListOfCategory;
    private int mCategoryID;

    public CategoriesProductFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();

    }

    public void productListOfCategory(){
        mRepository.fetchProductsOfCategory(mCategoryID);
    }

    public void setCategoryID(int mCategoryID) {
        this.mCategoryID = mCategoryID;
    }

    public LiveData<List<Product>> getProductListOfCategory() {
        return mProductListOfCategory;
    }
}
