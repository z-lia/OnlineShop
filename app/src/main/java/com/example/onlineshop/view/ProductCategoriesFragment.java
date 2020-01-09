package com.example.onlineshop.view;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductCategoriesFragment extends ConnectionFragment {

    public static ProductCategoriesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProductCategoriesFragment fragment = new ProductCategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ProductCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_categories, container, false);
    }

}
