package com.example.onlineshop.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
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
    private MutableLiveData<List<CategoriesItem>> mSubCategoryItemsLiveData;

    private MutableLiveData<List<Product>> mProductsOfSubCategoryMLiveData;
    private MutableLiveData<Product> mSliderProductsLiveData;

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
        mCategoryItemsLiveData = new MutableLiveData<>();
        mSubCategoryItemsLiveData = new MutableLiveData<>();
        mSliderProductsLiveData = new MutableLiveData<>();
    }

    public void fetchAllSubCategories() {
        List<CategoriesItem> parentCategories = new ArrayList<>();
        List<Integer> parentIDs = new ArrayList<>();
        final List<CategoriesItem> subCategories = new ArrayList<>();

        Map<String, String> queries = new HashMap<>();
        queries.putAll(mProductQueries);
        //get all parent category IDs and save them in parentIDs List
        for (int i = 0; i < mCategoryItemsLiveData.getValue().size(); i++) {
            if (mCategoryItemsLiveData.getValue().get(i).getParent() == 0) {
                parentIDs.add(mCategoryItemsLiveData.getValue().get(i).getId());
                Log.d(TAG, "parentId = " + mCategoryItemsLiveData.getValue().get(i).getId());
            }
        }

        mSubCategoryItemsLiveData.setValue(new ArrayList<CategoriesItem>());
        for (int i = 0; i < parentIDs.size(); i++) {
            queries.put("parent", parentIDs.get(i) + "");
            Call<List<CategoriesItem>> call = mIwooCommerceService.getAllCategories(queries);
            call.enqueue(new Callback<List<CategoriesItem>>() {
                @Override
                public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse subcategories: " + response.body().size());
                        subCategories.addAll(response.body());
                        mSubCategoryItemsLiveData.getValue().addAll(response.body());
                    } else {
                        Log.d(TAG, "onResponse subcategories: is not successful ");
                    }
                }

                @Override
                public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                    Log.e(TAG, t.getMessage(), t);
                }
            });
        }

    }

    public MutableLiveData<List<Product>> fetchSearchProducts(final String searchQuery) {
        final Map<String, String> queryParameters = new HashMap<>();
        queryParameters.putAll(mProductQueries);
        queryParameters.put("search", searchQuery);
        final MutableLiveData<List<Product>> searchedProductsMLiveData = new MutableLiveData<>();
        Call<List<Product>> call = mIwooCommerceService.getLatestProducts(queryParameters);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse search: " + searchQuery);
                    List<Product> searchProducts = response.body();
                    searchedProductsMLiveData.setValue(searchProducts);
                } else {
                    Log.d(TAG, "onResponse search: is not successful ");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
        return searchedProductsMLiveData;
    }

    public void fetchSliderProducts() {
        Call<Product> call = mIwooCommerceService.getProductByID(608, mProductQueries);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse sliderProducts: ");
                    Product sliderProducts = response.body();
                    mSliderProductsLiveData.setValue(sliderProducts);
                } else {
                    Log.d(TAG, "onResponse sliderProducts: is not successful ");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

    }

    public MutableLiveData<List<CategoriesItem>> fetchSubCategories(int parentId) {
        Map<String, String> queries = new HashMap<>();
        queries.putAll(mProductQueries);
        queries.put("parent", parentId + "");
        Call<List<CategoriesItem>> call = mIwooCommerceService.getAllCategories(queries);
        final MutableLiveData<List<CategoriesItem>> subCategoriesLiveData = new MutableLiveData<>();

        call.enqueue(new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse subcategories: ");
                    List<CategoriesItem> categoriesItems = response.body();
                    subCategoriesLiveData.setValue(categoriesItems);
                } else {
                    Log.d(TAG, "onResponse subcategories: is not successful ");

                }
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
        return subCategoriesLiveData;
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
                    fetchAllSubCategories();
                } else {
                    Log.d(TAG, "onResponse categories: is not successful ");

                }
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchSpecificTypeOfProductsAsync(final String query) {
        Map<String, String> typeProductQueries = new HashMap<>();
        typeProductQueries.putAll(mProductQueries);
        typeProductQueries.put("orderby", query);
        Call<List<Product>> call = mIwooCommerceService.getLatestProducts(typeProductQueries);
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
        fetchSliderProducts();
    }

    public MutableLiveData<Product> fetchProductById(int id) {
        Call<Product> call = mIwooCommerceService.getProductByID(id, mProductQueries);
        final MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "successful by Id: " + productMutableLiveData);
                    Product product = response.body();
                    productMutableLiveData.setValue(product);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

        return productMutableLiveData;
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

    public MutableLiveData<List<CategoriesItem>> getSubCategoryItemsLiveData() {
        return mSubCategoryItemsLiveData;
    }

    public MutableLiveData<List<Product>> fetchProductsOfCategory(final int categoryID) {
        final MutableLiveData<List<Product>> productsMutableLiveDate = new MutableLiveData<>();
        Map<String, String> queries = new HashMap<>();
        queries.putAll(mProductQueries);
        queries.put("category", String.valueOf(categoryID));
        Call<List<Product>> call = mIwooCommerceService.getProductsOfCategoryById(queries);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

//                mProductsOfSubCategoryMLiveData.setValue(products);
                if (response.isSuccessful()) {
                    Log.d(TAG , "is successful" +"products of category "+categoryID);
                    List<Product> products = response.body();
                    productsMutableLiveDate.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });

        return productsMutableLiveDate;
    }

    public MutableLiveData<Product> getSliderProducts() {
        return mSliderProductsLiveData;
    }

    public void register(String username, String password) {
        Map<String, String> queries = new HashMap<>();
        queries.putAll(mProductQueries);
        Customer user = new Customer(username, password);
        Call<Customer> call = mIwooCommerceService.postCreateCustomer(queries, user);
    }
}
