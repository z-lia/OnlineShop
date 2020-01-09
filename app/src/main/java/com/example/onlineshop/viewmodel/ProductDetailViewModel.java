package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.WooCommerceRepository;

import java.util.ArrayList;

public class ProductDetailViewModel extends AndroidViewModel {
    private WooCommerceRepository mRepository;
    private int mProductID;


    private MutableLiveData<Product> mProductMutableLiveData;


    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
        mProductMutableLiveData = mRepository.fetchProductById(mProductID);
    }

    public void fetchProductByID(int productID) {
        mProductMutableLiveData = mRepository.fetchProductById(productID);
    }

    public ArrayList<String> getProductImages(){
        ArrayList <String> sourceOfImages = new ArrayList<>();
        for(int i =0; i<mProductMutableLiveData.getValue().getImages().size();i++)
        {
            sourceOfImages.add(mProductMutableLiveData.getValue().getImages().get(i).getSrc());
        }

        return sourceOfImages;
    }

    public String getProductName() {
        return mProductMutableLiveData.getValue().getName();
    }

    public String getProductPrice() {
        if(mProductMutableLiveData.getValue() == null)
            return "";
        else
        return mProductMutableLiveData.getValue().getPrice();
    }

    public String getProductPriceOnSale() {
        String priceOnSale = "";
        if (mProductMutableLiveData.getValue().isOnSale())
            priceOnSale = mProductMutableLiveData.getValue().getSalePrice();
        return priceOnSale;
    }

    public boolean isOnSale(){
        return mProductMutableLiveData.getValue().isOnSale();
    }

    public String getProductDescription() {
        return mProductMutableLiveData.getValue().getDescription();

    }


    public MutableLiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }
}
