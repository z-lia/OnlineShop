package com.example.onlineshop.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.databinding.FragmentProductListBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.HomeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends ConnectionFragment {

    private static final String PRODUCT_LIST_TYPE = "product list type";
    private RecyclerAdapter mRecyclerAdapterProducts;
    private HomeViewModel mHomeViewModel;
    private FragmentProductListBinding mBinding;
    private String mProductListType;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(String productListType) {

        Bundle args = new Bundle();
        args.putString( PRODUCT_LIST_TYPE, productListType);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mProductListType = getArguments().getString(PRODUCT_LIST_TYPE);
        setObservers();
    }

    private void setObservers() {
        mHomeViewModel.getLatestProductLiveData().observe(this, products -> {
            setupAdapter(products);
            Log.d("Observer", "" + products.size());
        });

    }

    private void setupAdapter(List<Product> products) {

        if (mRecyclerAdapterProducts == null) {
            mRecyclerAdapterProducts = new RecyclerAdapter(products, getContext());
            mBinding.recyclerViewProducts.setAdapter(mRecyclerAdapterProducts);
        } else {
            mRecyclerAdapterProducts.setProductList(products);
            mRecyclerAdapterProducts.notifyDataSetChanged();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        mBinding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();

    }

}
