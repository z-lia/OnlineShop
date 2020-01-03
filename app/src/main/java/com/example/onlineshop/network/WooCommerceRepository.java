package com.example.onlineshop.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WooCommerceRepository {
    private static final String BASE_URL = " https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";

    public static final String CONSUMER_KEY = "ck_afcde41bdfa7c7ab871bd26f950ce0101ac96c92";
    public static final String CONSUMER_SECRET = "cs_48ed218ae80a2d28cf1b88378f66f75ead30d99a";
    private static final String TAG = "WooWooCommerceFetcherC";
    private static final String TAG_ONResponse = "Onresopnse";

    private static WooCommerceRepository sInstance;

    private Map<String, String> mProductQueries;
    private Retrofit mRetrofit;
    private IWooCommerceService mIwooCommerceService;

    private MutableLiveData<List<Product>> mLatestProductLiveData;
    private MutableLiveData<List<Product>> mBestProductsLiveData;
    private MutableLiveData<List<Product>> mMostPopularProductsLiveData;

    private MutableLiveData<List<CategoriesItem>> mCategoryItemsLiveData;
    private MutableLiveData<Product> mProductMutableLiveData;

    public static WooCommerceRepository getsInstance() {
        if (sInstance == null)
            sInstance = new WooCommerceRepository();
        return sInstance;
    }

    private WooCommerceRepository() {
        mProductQueries = new HashMap<String, String>() {{
            put("consumer_key", "ck_1c7bb6ff68953b8e57ed7da1487cd80ff87e332b");
            put("consumer_secret", "cs_256f95ec8c941ffdcb655cccf462e0af9b1ebbb7");
        }};

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mIwooCommerceService = mRetrofit.create(IWooCommerceService.class);
        mBestProductsLiveData = new MutableLiveData<>();
        mLatestProductLiveData = new MutableLiveData<>();
        mMostPopularProductsLiveData = new MutableLiveData<>();
        mProductMutableLiveData = new MutableLiveData<>();
        mCategoryItemsLiveData = new MutableLiveData<>();
    }

    public void fetchAllCategories() {
        Call<List<CategoriesItem>> call = mIwooCommerceService.getAllCategories(mProductQueries);
        call.enqueue(new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse categories: ");
                    List<CategoriesItem> categoriesItems = response.body();
                    mCategoryItemsLiveData.setValue(categoriesItems);
                }
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchSpecificTypeOfProductsAsync(final String query) {
        mProductQueries.put("orderby", query);
        Call<List<Product>> call = mIwooCommerceService.getLatestProducts(mProductQueries);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "in on response");
                    List<Product> orderedProducts = response.body();
                    switch (query) {
                        case "date":
                            Log.d(TAG, "in on response date");
                            mLatestProductLiveData.setValue(orderedProducts);
                            break;
                        case "popularity":
                            Log.d(TAG, "in on response popularity");
                            mMostPopularProductsLiveData.setValue(orderedProducts);
                            break;
                        case "rating":
                            Log.d(TAG, "in on response rating");
                            mBestProductsLiveData.setValue(orderedProducts);
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchSpecificProducts() {
        fetchSpecificTypeOfProductsAsync("date");
        fetchSpecificTypeOfProductsAsync("popularity");
        fetchSpecificTypeOfProductsAsync("rating");
        fetchAllCategories();
    }

    public void fetchProductById(int id) {
        Call<Product> call = mIwooCommerceService.getProduct(id, mProductQueries);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();
                    mProductMutableLiveData.setValue(product);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchAllProductAsync() {
        Call<List<Product>> call = mIwooCommerceService.getProductList(mProductQueries);
        call.enqueue(getRetrofitCallBack());
    }

    private Callback<List<Product>> getRetrofitCallBack() {
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    //iProductFetcherCallback.setAdapterOnResponse(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        };
    }

    public MutableLiveData<List<Product>> getLatestProductLiveData() {
//        fetchSpecificTypeOfProductsAsync("date");
        return mLatestProductLiveData;
    }

    public MutableLiveData<List<Product>> getBestProductsLiveData() {
//        fetchSpecificTypeOfProductsAsync("rating");
        return mBestProductsLiveData;
    }

    public MutableLiveData<List<Product>> getMostPopularProductsLiveData() {
//        fetchSpecificTypeOfProductsAsync("popularity");
        return mMostPopularProductsLiveData;
    }

    public MutableLiveData<List<CategoriesItem>> getCategoryItemsLiveData() {
        return mCategoryItemsLiveData;
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return mProductMutableLiveData;
    }
}
