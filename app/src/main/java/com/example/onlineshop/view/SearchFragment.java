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
import com.example.onlineshop.databinding.FragmentSearchBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.RecyclerAdapter;
import com.example.onlineshop.viewmodel.SearchViewModel;

import java.util.List;
import java.util.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private FragmentSearchBinding mBinding;
    private SearchViewModel mSearchViewModel;
    private RecyclerAdapter mRecyclerAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
//        mSearchViewModel.getSearchProducts().observe(this, new Observer<List<Product>>() {
//            @Override
//            public void onChanged(List<Product> products) {
//                setAdapter(products);
//            }
//        });
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbarSearch);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        mBinding.recyclerViewSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                  updateItems(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String query = QueryPreferences.getLastQuery(getContext());
//                searchView.setQuery(query, false);
//            }
//        });
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
