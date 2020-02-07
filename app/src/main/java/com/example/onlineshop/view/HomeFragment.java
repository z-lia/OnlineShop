package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.adapter.CategoryRecyclerAdapter;
import com.example.onlineshop.view.adapter.RecyclerAdapter;
import com.example.onlineshop.databinding.FragmentHomeBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.viewmodel.HomeViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends ConnectionFragment {

    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mBinding;

    private RecyclerAdapter mRecyclerAdapterBestProducts;
    private RecyclerAdapter mRecyclerAdapterLatestProducts;
    private RecyclerAdapter mRecyclerAdapterPopularProducts;
    private CategoryRecyclerAdapter mRecyclerAdapterCategories;

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
                Log.d("Observer", "Latest" + products.size());
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

        mHomeViewModel.getCategoriesLiveData().observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoryItems) {
                setUpAdapterCategories(categoryItems);
            }
        });

        mHomeViewModel.getSliderProducts().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                setFlipperImage();
            }
        });
    }

    private void setUpAdapterCategories(List<CategoriesItem> categoryItems) {
        if (mRecyclerAdapterCategories == null) {
            mRecyclerAdapterCategories = new CategoryRecyclerAdapter(mHomeViewModel.getCategoriesLiveData().getValue(), getContext());
            mBinding.recyclerViewCategories.setAdapter(mRecyclerAdapterCategories);
        } else {
            mRecyclerAdapterCategories.setCategoryItems(categoryItems);
            mRecyclerAdapterCategories.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initUI();
        setViewFlipper();
        setFlipperImage();
        setClickListener();
        return mBinding.getRoot();
    }

    private void setClickListener() {

        //jaditarin
        mBinding.textViewListAllLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProductListActivity.newIntent(HomeFragment.this.getContext(),
                        ProductListActivity.LATEST_PRODUCT));
            }
        });


        mBinding.textViewListAllBestProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProductListActivity.newIntent(HomeFragment.this.getContext(),
                        ProductListActivity.MOST_VISIT_PRODUCT));
            }
        });


        mBinding.textViewListAllPopularProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProductListActivity.newIntent(HomeFragment.this.getContext(),
                        ProductListActivity.POPULAR_PRODUCT));
            }
        });


    }

    private void setViewFlipper() {
        mBinding.viewFlipper.setFlipInterval(2000);
        mBinding.viewFlipper.setAutoStart(true);

        //animation
        //mBinding.viewFlipper.setAnimation();
        mBinding.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.in_from_right));
        mBinding.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.out_from_left  ));
    }

    private void setFlipperImage() {

        Product sliderProducts = mHomeViewModel.getSliderProducts().getValue();
        if (sliderProducts != null) {
            for (int i = 0; i < sliderProducts.getImages().size(); i++) {
                ImageView imageView = new ImageView(getContext());
                Picasso.get()
                        .load(sliderProducts.getImages().get(i).getSrc())
                        .into(imageView);
                mBinding.viewFlipper.addView(imageView);
            }
        }
//        else{
//            int []imageId = { R.drawable.image_placeholder ,
//                    R.drawable.image_placeholder2 ,
//            R.drawable.imageloadingplaceholder};
//            for (int i = 0; i < imageId.length; i++) {
//                ImageView imageView = new ImageView(getContext());
//                imageView.setBackgroundResource(imageId[i]);
//                mBinding.viewFlipper.addView(imageView);
//            }
//        }
    }

    private void initUI() {
        mBinding.recyclerViewLatestProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.recyclerViewBestProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.recyclerViewPopularProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpAdapterBest(List<Product> products) {
        if (mRecyclerAdapterBestProducts == null) {
            mRecyclerAdapterBestProducts = new RecyclerAdapter(mHomeViewModel.getBestProductsLiveData().getValue(), getContext());
            mBinding.recyclerViewBestProducts.setAdapter(mRecyclerAdapterBestProducts);
        } else {
            mRecyclerAdapterBestProducts.setProductList(products);
            mRecyclerAdapterBestProducts.notifyDataSetChanged();
        }
    }

    private void setupAdapterLatest(List<Product> products) {
        if (mRecyclerAdapterLatestProducts == null) {
            mRecyclerAdapterLatestProducts = new RecyclerAdapter(mHomeViewModel.getLatestProductLiveData().getValue(), getContext());
            mBinding.recyclerViewLatestProducts.setAdapter(mRecyclerAdapterLatestProducts);
        } else {
            mRecyclerAdapterLatestProducts.setProductList(products);
            mRecyclerAdapterLatestProducts.notifyDataSetChanged();
        }
    }

    private void setUpAdapterMostPopular(List<Product> products) {
        if (mRecyclerAdapterPopularProducts == null) {
            mRecyclerAdapterPopularProducts = new RecyclerAdapter(mHomeViewModel.getMostPopularProductsLiveData().getValue(), getContext());
            mBinding.recyclerViewPopularProducts.setAdapter(mRecyclerAdapterPopularProducts);
        } else {
            mRecyclerAdapterPopularProducts.setProductList(products);
            mRecyclerAdapterPopularProducts.notifyDataSetChanged();
        }
    }
}
