package com.example.onlineshop.network;

import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IWooCommerceService {
    @GET("products/?")
    Call <List<Product>> getProductList(@QueryMap Map<String, String> queries);

    @GET("products/?")
    Call <List<Product>> getLatestProducts(@QueryMap Map<String, String> productQueries );
}
