package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;

public abstract class SingleFragmentActivityWithToolbar extends SingleFragmentActivity {


    //public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_with_toolbar);

        setToolbar();
        setFragment();

    }

    private void setToolbar(){

        Toolbar mToolbar = findViewById(R.id.toolbar_single_fragment_activity);
        setSupportActionBar(mToolbar);
    }
    private void setFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null)
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container ,createFragment())
                    .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.search_home);

        searchMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(SearchActivity.newIntent(SingleFragmentActivityWithToolbar.this));
                return true;
            }
        });

        MenuItem cartMenuItem = menu.findItem(R.id.cart);

        cartMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(CartActivity.newIntent(SingleFragmentActivityWithToolbar.this));
                return true;
            }
        });


        return true;
    }

}
