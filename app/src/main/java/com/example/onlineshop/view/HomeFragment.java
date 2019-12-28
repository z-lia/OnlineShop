package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.RecyclerAdapter;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewmodel.HomeViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {

    private RecyclerView mRecyclerViewProducts;
    private RecyclerAdapter mRecyclerAdapter;

    private HomeViewModel mViewModel;
    private RecyclerView mRecyclerViewBestProducts;
    private RecyclerAdapter mRecyclerAdapterBestProducts;

    private RecyclerView mRecyclerViewLatestProducts;
    private RecyclerAdapter mRecyclerAdapterLatestProducts;

    private RecyclerView mRecyclerViewMostPopularProducts;
    private RecyclerAdapter mRecyclerAdapterPopularProducts;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        setObservers();
    }

    private void setObservers() {
        mViewModel.getLatestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapterLatest(products);
            }
        });

        mViewModel.getBestProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setUpAdapterBest(products);
            }
        });

        mViewModel.getMostPopularProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setUpAdapterMostPopular(products);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);

        return view;
    }



    private void initUI(View view) {
        mRecyclerViewBestProducts = view.findViewById(R.id.recycler_view_bestProducts);
        mRecyclerViewLatestProducts = view.findViewById(R.id.recycler_view_LatestProducts);
        mRecyclerViewMostPopularProducts = view.findViewById(R.id.recycler_view_popularProducts);

        mRecyclerViewProducts = view.findViewById(R.id.recycler_view_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewProducts.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManagerLatest = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewLatestProducts.setLayoutManager(linearLayoutManagerLatest);

        LinearLayoutManager linearLayoutManagerBest = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewBestProducts.setLayoutManager(linearLayoutManagerBest);
//        mRecyclerAdapterBestProducts = new RecyclerAdapter(mViewModel.getBestProductsLiveData().getValue(), getContext());
//        mRecyclerViewBestProducts.setAdapter(mRecyclerAdapterBestProducts);

        LinearLayoutManager linearLayoutManagerPopular = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewMostPopularProducts.setLayoutManager(linearLayoutManagerPopular);
    }

    private void setupAdapter(List<Product> products) {
        if (mRecyclerAdapter == null) {
            mRecyclerAdapter = new RecyclerAdapter(products, getContext());
            mRecyclerViewProducts.setAdapter(mRecyclerAdapter);
        } else {
            mRecyclerAdapter.setProductList(products);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void setUpAdapterBest(List<Product> products) {
        if (mRecyclerAdapterBestProducts == null) {
            mRecyclerAdapterBestProducts = new RecyclerAdapter(mViewModel.getBestProductsLiveData().getValue(), getContext());
            mRecyclerViewBestProducts.setAdapter(mRecyclerAdapterBestProducts);
        } else {
            mRecyclerAdapterBestProducts.setProductList(products);
            mRecyclerAdapterBestProducts.notifyDataSetChanged();
        }
    }

    private void setupAdapterLatest(List<Product> products) {
        if (mRecyclerAdapterLatestProducts == null) {
            mRecyclerAdapterLatestProducts = new RecyclerAdapter(mViewModel.getLatestProductLiveData().getValue(), getContext());
            mRecyclerViewLatestProducts.setAdapter(mRecyclerAdapterBestProducts);
        } else {
            mRecyclerAdapterLatestProducts.setProductList(products);
            mRecyclerAdapterLatestProducts.notifyDataSetChanged();
        }
    }

    private void setUpAdapterMostPopular(List<Product> products) {
        if (mRecyclerAdapterPopularProducts == null) {
            mRecyclerAdapterPopularProducts = new RecyclerAdapter(mViewModel.getBestProductsLiveData().getValue(), getContext());
            mRecyclerViewMostPopularProducts.setAdapter(mRecyclerAdapterPopularProducts);
        } else {
            mRecyclerAdapterPopularProducts.setProductList(products);
            mRecyclerAdapterPopularProducts.notifyDataSetChanged();
        }
    }
}
