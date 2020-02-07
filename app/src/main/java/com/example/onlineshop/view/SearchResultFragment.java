package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentSearchResultBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.SearchViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    private static final String ARGS_SEARCH_QUERY = "args search query";
    private FragmentSearchResultBinding mBinding;
    private SearchViewModel mSearchViewModel;
    private RecyclerAdapter mRecyclerAdapter;
    private String mSearchQuery;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance(String query) {

        Bundle args = new Bundle();
        args.putString(ARGS_SEARCH_QUERY, query);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mSearchQuery = getArguments().getString(ARGS_SEARCH_QUERY);
    }

    private void setAdapter(List<Product> products) {
        if (mRecyclerAdapter == null) {
            mRecyclerAdapter = new RecyclerAdapter(products, getContext());
            mBinding.recyclerViewSearchResult.setAdapter(mRecyclerAdapter);
        } else {
            mRecyclerAdapter.setProductList(products);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbarSearch);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        mBinding.toolbarSearch.setTitle(mSearchQuery);
        mBinding.recyclerViewSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        updateItems(mSearchQuery);
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search_home);
        searchMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(SearchActivity.newIntent(getContext()));
                getActivity().finish();
                return true;
            }
        });

        MenuItem cartMenuItem = menu.findItem(R.id.cart);
        cartMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(CartActivity.newIntent(getContext()));
                getActivity().finish();
                return true;
            }
        });
    }

    private void updateItems(String query) {
        mSearchViewModel.searchProduct(query);
        mSearchViewModel.getSearchProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapter(products);
            }
        });
    }
}
