package com.example.onlineshop.view;


import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductCategoriesBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.CategoriesViewModel;

import java.util.List;

public class ProductsOfCategoryFragment extends ConnectionFragment {

    private static final String ARGS_CATEGORY_ID = "category id";
    private static final String ARGS_CATEGORY_NAME = "category name";
    private FragmentProductCategoriesBinding mBinding;
    private CategoriesViewModel mCategoriesViewModel;
    private int mCategoryID;
    private CategoriesItem mCategory;
    private String mCategoryName;
    private MutableLiveData<List<Product>> mProductsLiveData = new MutableLiveData<>();
    private RecyclerAdapter mRecyclerAdapterProducts;

    public static ProductsOfCategoryFragment newInstance(int categoryId , String categoryName) {
        
        Bundle args = new Bundle();
        args.putInt(ARGS_CATEGORY_ID , categoryId);
        args.putString(ARGS_CATEGORY_NAME , categoryName);
        ProductsOfCategoryFragment fragment = new ProductsOfCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ProductsOfCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        mCategoryID = getArguments().getInt(ARGS_CATEGORY_ID);
        mCategoryName = getArguments().getString(ARGS_CATEGORY_NAME);
    //mCategoriesViewModel.getCategoryByID(mCategoryID);
        mProductsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapter(products);
            }
        });

    }

    private void setAdapter(List<Product> products) {

        if (mRecyclerAdapterProducts == null) {
            mRecyclerAdapterProducts = new RecyclerAdapter(products, getContext());
            mBinding.recyclerViewProductsOfCategory.setAdapter(mRecyclerAdapterProducts);
        } else {
            mRecyclerAdapterProducts.setProductList(products);
            mRecyclerAdapterProducts.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_product_categories, container, false);
         mBinding.progressbar.setIndeterminate(true);
         new ProgressLoad().execute();
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbarCategory);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mCategoryName);
        mBinding.recyclerViewProductsOfCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return mBinding.getRoot();
    }

    public class ProgressLoad extends AsyncTask <Void , Void , Void>{

        @Override
        protected Void doInBackground(Void... voids) {
           mProductsLiveData =  mCategoriesViewModel.fetchProductOfCategory(mCategoryID);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mBinding.progressbar.setIndeterminate(false);
            mBinding.progressbar.setVisibility(View.GONE);
            mProductsLiveData.observe(ProductsOfCategoryFragment.this, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    setAdapter(products);
                }
            });
        }
    }

}
