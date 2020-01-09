package com.example.onlineshop.view;


import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.ViewPagerSliderAdapter;
import com.example.onlineshop.viewmodel.HomeViewModel;
import com.example.onlineshop.viewmodel.ProductDetailViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends ConnectionFragment {

    private static final String ARGS_PRODUCT = "product";
    private int mProductID;
    private HomeViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private ViewPagerSliderAdapter mViewPagerSliderAdapter;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int productId) {

        Bundle args = new Bundle();
        args.putInt(ARGS_PRODUCT , productId);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductID = getArguments().getInt(ARGS_PRODUCT);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        //mProductDetailViewModel.setProductID(mProductID);
        mViewModel.fetchProductByID(mProductID);

        mViewModel.getProductMutableLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                //mViewModel.fetchProductByID(mProductID);
                mBinding.textViewProductName.setText(mViewModel.getProductMutableLiveData().getValue().getName());
//                mBinding.textViewProductName.setText(mViewModel.getProductName());
                mBinding.textViewProductPrice.setText(mViewModel.getProductMutableLiveData().getValue().getPrice());
                //why null ???
//                mBinding.textViewProductPrice.setText(mViewModel.getProductPrice());
//                if(mViewModel.isOnSale()){
                if(mViewModel.getProductMutableLiveData().getValue().isOnSale()){
//                    mBinding.textViewProductPriceOnSale.setText(mViewModel.getProductMutableLiveData().getValue().getSalePrice());
                    mBinding.textViewProductPrice.setPaintFlags( mBinding.textViewProductPrice.getPaintFlags() |Paint.STRIKE_THRU_TEXT_FLAG);
                }
                setProductDescription();
                setViewPagerAdapter();

            }
        });
    }


    public void setProductDescription(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.textViewProductDescription.setText(Html.fromHtml(mViewModel.getProductMutableLiveData().getValue().getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            mBinding.textViewProductDescription.setText(
                    Html.fromHtml(mViewModel.getProductMutableLiveData().getValue().getDescription()));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
//        mBinding.setMViewModel(mViewModel);
        setViewPagerAdapter();

        return mBinding.getRoot();
    }
    public void setViewPagerAdapter(){
        ArrayList<String> sourceOfImages = new ArrayList<>() ;
        if(mViewModel.getProductMutableLiveData().getValue()!=null) {
             sourceOfImages = mViewModel.getProductImages();
        }
        mViewPagerSliderAdapter = new ViewPagerSliderAdapter(sourceOfImages, getContext());
        mBinding.viewPager2Slider.setAdapter(mViewPagerSliderAdapter);
    }

}
