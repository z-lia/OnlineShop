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

public class CategoriesViewModel extends AndroidViewModel {

    private WooCommerceRepository mRepository;
    private LiveData<List<CategoriesItem>> mCategoriesListLiveData;
    private MutableLiveData<List<CategoriesItem>> mParentCategoriesLiveData = new MutableLiveData<List<CategoriesItem>>();
    //private MutableLiveData<List<CategoriesItem>> mSubCategoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CategoriesItem>> mSubCategoriesLiveData2 = new MutableLiveData<>();
    private MutableLiveData <List<Product>> mProductOfCategory = new MutableLiveData<>();

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        mRepository = WooCommerceRepository.getsInstance();
        //mRepository.fetchAllCategories();
        mCategoriesListLiveData = mRepository.getCategoryItemsLiveData();
        setParentCategoriesLiveData();
        //setSubCategoriesLiveData();
        mSubCategoriesLiveData2 = mRepository.getSubCategoryItemsLiveData();
    }

    public MutableLiveData<List<Product>> fetchProductOfCategory(int categoryId){
       return mRepository.fetchProductsOfCategory(categoryId);
    }

    private void setParentCategoriesLiveData() {
        List<CategoriesItem> parentCategories = new ArrayList<>();
        for (int i = 0; i < mCategoriesListLiveData.getValue().size(); i++) {
            if (mCategoriesListLiveData.getValue().get(i).getParent() == 0)
                parentCategories.add(mCategoriesListLiveData.getValue().get(i));
        }
        mParentCategoriesLiveData.setValue(parentCategories);
    }

//    private void setSubCategoriesLiveData() {
//        List<CategoriesItem> subCategories = new ArrayList<>();
//
//        for (int j = 0; j < mParentCategoriesLiveData.getValue().size(); j++) {
//            for (int i = 0; i < mCategoriesListLiveData.getValue().size(); i++) {
//                if (mCategoriesListLiveData.getValue().get(i).getParent() == mParentCategoriesLiveData.getValue().get(j).getId())
//                    subCategories.add(mCategoriesListLiveData.getValue().get(i));
//            }
//        }
//        mSubCategoriesLiveData.setValue(subCategories);
//    }

    public LiveData<List<CategoriesItem>> getParentCategoriesListLiveData() {
        return mParentCategoriesLiveData;
    }


    public LiveData<List<CategoriesItem>> getCategoriesListLiveData() {
        return mCategoriesListLiveData;
    }

//    public MutableLiveData<List<CategoriesItem>> getSubCategoriesLiveData() {
//        return mSubCategoriesLiveData;
//    }

    public MutableLiveData<List<CategoriesItem>> getSubCategoriesLiveData2() {
        return mSubCategoriesLiveData2;
    }

    public MutableLiveData<List<CategoriesItem>> getSubCategoriesByParentIDLiveData(int parentId) {
        List<CategoriesItem> subCategories = new ArrayList<>();
        for(int i =0; i<mSubCategoriesLiveData2.getValue().size(); i++)
        {
            if(mSubCategoriesLiveData2.getValue().get(i).getParent()== parentId)
                subCategories.add(mSubCategoriesLiveData2.getValue().get(i));
        }
        MutableLiveData<List<CategoriesItem>> subCategoriesLiveData = new MutableLiveData<>();
        subCategoriesLiveData.setValue(subCategories);
        return subCategoriesLiveData;
    }

    public CategoriesItem getCategoryByID(int categoryId){
        CategoriesItem categoryItem = new CategoriesItem();
        for(int i =0; i<mSubCategoriesLiveData2.getValue().size(); i++) {
            if (mSubCategoriesLiveData2.getValue().get(i).getId() == categoryId)
                categoryItem = mSubCategoriesLiveData2.getValue().get(i);
        }

        return categoryItem;
    }

}
