package com.example.onlineshop.view;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SearchResultActivity extends SingleFragmentActivity {

    private static final String EXTRA_QUERY_SEARCH ="com.example.onlineshop.view query_search" ;

    @Override
    public Fragment createFragment() {
        String query = getIntent().getStringExtra(EXTRA_QUERY_SEARCH);
        return SearchResultFragment.newInstance(query);
    }

    public static Intent newIntent(Context context , String query){
        Intent intent = new Intent(context , SearchResultActivity.class);
        intent.putExtra(EXTRA_QUERY_SEARCH, query);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
