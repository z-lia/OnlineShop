package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

    private HomeViewModel mHomeViewModel;
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
        //getActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        setObservers();
    }

    private void setObservers() {
        mHomeViewModel.getLatestProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setupAdapterLatest(products);
                Log.d("Observer", "Latest"+products.size());
            }
        });

        mHomeViewModel.getBestProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setUpAdapterBest(products);
            }
        });

        mHomeViewModel.getMostPopularProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setUpAdapterMostPopular(products);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initUI(view);

        return view;
    }

    private void initUI(View view) {
        mRecyclerViewBestProducts = view.findViewById(R.id.recycler_view_bestProducts);
        mRecyclerViewLatestProducts = view.findViewById(R.id.recycler_view_LatestProducts);
        mRecyclerViewMostPopularProducts = view.findViewById(R.id.recycler_view_popularProducts);

        mRecyclerViewLatestProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mRecyclerViewBestProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mRecyclerAdapterBestProducts = new RecyclerAdapter(mHomeViewModel.getBestProductsLiveData().getValue(), getContext());
//        mRecyclerViewBestProducts.setAdapter(mRecyclerAdapterBestProducts);

        mRecyclerViewMostPopularProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpAdapterBest(List<Product> products) {
        if (mRecyclerAdapterBestProducts == null) {
            mRecyclerAdapterBestProducts = new RecyclerAdapter(mHomeViewModel.getBestProductsLiveData().getValue(), getContext());
            mRecyclerViewBestProducts.setAdapter(mRecyclerAdapterBestProducts);
        } else {
            mRecyclerAdapterBestProducts.setProductList(products);
            mRecyclerAdapterBestProducts.notifyDataSetChanged();
        }
    }

    private void setupAdapterLatest(List<Product> products) {
        if (mRecyclerAdapterLatestProducts == null) {
            mRecyclerAdapterLatestProducts = new RecyclerAdapter(mHomeViewModel.getLatestProductLiveData().getValue(), getContext());
            mRecyclerViewLatestProducts.setAdapter(mRecyclerAdapterLatestProducts);
        } else {
            mRecyclerAdapterLatestProducts.setProductList(products);
            mRecyclerAdapterLatestProducts.notifyDataSetChanged();
        }
    }

    private void setUpAdapterMostPopular(List<Product> products) {
        if (mRecyclerAdapterPopularProducts == null) {
            mRecyclerAdapterPopularProducts = new RecyclerAdapter(mHomeViewModel.getMostPopularProductsLiveData().getValue(), getContext());
            mRecyclerViewMostPopularProducts.setAdapter(mRecyclerAdapterPopularProducts);
        } else {
            mRecyclerAdapterPopularProducts.setProductList(products);
            mRecyclerAdapterPopularProducts.notifyDataSetChanged();
        }
    }
}
