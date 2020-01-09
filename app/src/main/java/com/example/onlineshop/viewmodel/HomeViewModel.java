package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData <List<Product>> mLatestProductLiveData;
    private MutableLiveData <List<Product>> mBestProductsLiveData;
    private MutableLiveData <List<Product>> mMostPopularProductsLiveData;

    private MutableLiveData <List<CategoriesItem>> mCategoriesLiveData;

    private MutableLiveData<Product> mProductMutableLiveData;

    private WooCommerceRepository mRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        mRepository = WooCommerceRepository.getsInstance();
        mLatestProductLiveData = mRepository.getLatestProductLiveData();
        mBestProductsLiveData = mRepository.getBestProductsLiveData();
        mMostPopularProductsLiveData = mRepository.getMostPopularProductsLiveData();
        mCategoriesLiveData = mRepository.getCategoryItemsLiveData();
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

    public void fetchProductByID(int productID){
        mProductMutableLiveData  = mRepository. fetchProductById(productID);
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }

    public ArrayList<String> getProductImages(){
        ArrayList <String> sourceOfImages = new ArrayList<>();
        for(int i =0; i<mProductMutableLiveData.getValue().getImages().size();i++)
        {
            sourceOfImages.add(mProductMutableLiveData.getValue().getImages().get(i).getSrc());
        }

        return sourceOfImages;
    }

    public LiveData<List<CategoriesItem>> getCategoriesLiveData() {
        return mCategoriesLiveData;
    }
}
