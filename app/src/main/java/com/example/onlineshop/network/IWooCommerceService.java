package com.example.onlineshop.network;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IWooCommerceService {
    @GET("products")
    Call <List<Product>> getProductList(@QueryMap Map<String, String> queries);

    @GET("products")
    Call <List<Product>> getLatestProducts(@QueryMap Map<String, String> productQueries );

    @GET("products/categories")
    Call <List<CategoriesItem>> getAllCategories(@QueryMap Map<String, String> productQueries );

    //get subcategories by parent id : querypareameter: (parent , id)
    @GET("products/categories")
    Call <List<CategoriesItem>> getSubCategories(@QueryMap Map<String, String> productQueries );

    @GET("products/{id}")
    Call <Product> getProductByID(@Path("id")int productId , @QueryMap Map<String, String> productQueries );

//    @GET("products/categories/{id}")
//    Call <List<Product>> getProductsOfCategoryById( @Path("id") int categoryId ,@QueryMap Map<String, String> queries );
    @GET("products/")
    Call <List<Product>> getProductsOfCategoryById( @QueryMap Map<String, String> queries );

    @GET("products/608")
    Call<List<Product>> getSliderProducts(@QueryMap Map<String, String> mProductQueries);
}
