package com.example.onlineshop.view;


import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductCategoriesBinding;
import com.example.onlineshop.viewmodel.CategoriesViewModel;

public class ProductCategoriesFragment extends ConnectionFragment {

    private static final String ARGS_CATEGORY_ID = "category id";
    private FragmentProductCategoriesBinding mBinding;
    private CategoriesViewModel mCategoriesViewModel;

    public static ProductCategoriesFragment newInstance(int categoryId) {
        
        Bundle args = new Bundle();
        args.putInt(ARGS_CATEGORY_ID , categoryId);
        ProductCategoriesFragment fragment = new ProductCategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ProductCategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_product_categories, container, false);
         mBinding.progressbar.setIndeterminate(true);
         new ProgressLoad().execute();
        return mBinding.getRoot();
    }

    public class ProgressLoad extends AsyncTask <Void , Void , Void>{

        @Override
        protected Void doInBackground(Void... voids) {
           // mCategoriesViewModel.fetchProductsOfCategory();
            return null;
        }
    }

}
