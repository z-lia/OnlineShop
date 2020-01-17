package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentCategoryListBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.view.adapter.CategoryFragmentRecyclerAdapter;
import com.example.onlineshop.view.adapter.CategoryRecyclerAdapter;
import com.example.onlineshop.viewmodel.CategoriesViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends ConnectionFragment {

    private static final String ARG_TAB_SUBCATEGORY_ID = "parent category id";
    private FragmentCategoryListBinding mCategoryListBinding;
    private int mParentId;
    private CategoryFragmentRecyclerAdapter mCategoryAdapter;
    private CategoriesViewModel mCategoriesViewModel;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    public static CategoryListFragment newInstance(int parentCategoryId) {

        Bundle args = new Bundle();
        args.putInt(ARG_TAB_SUBCATEGORY_ID, parentCategoryId);
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentId = getArguments().getInt(ARG_TAB_SUBCATEGORY_ID);
        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCategoryListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false);
        initRecyclerView();
        return mCategoryListBinding.getRoot();
    }

    private void initRecyclerView() {
        mCategoryListBinding.recyclerViewSubcategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void setObserver() {
        mCategoriesViewModel.getSubCategoriesByParentIDLiveData(mParentId).observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                setAdapter(categoriesItems);
            }
        });
    }

    private void setAdapter(List<CategoriesItem> categoriesItems) {
        if (mCategoryAdapter == null){
            mCategoryAdapter = new CategoryFragmentRecyclerAdapter(categoriesItems , getContext());
            mCategoryListBinding.recyclerViewSubcategory.setAdapter(mCategoryAdapter);
        }
        else {
            mCategoryAdapter.setCategoryItems(categoriesItems);
            mCategoryAdapter.notifyDataSetChanged();
        }
    }

}
