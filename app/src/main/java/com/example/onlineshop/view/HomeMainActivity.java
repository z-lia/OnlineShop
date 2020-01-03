package com.example.onlineshop.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class HomeMainActivity extends SingleFragmentActivity {
    private ActivityMainBinding mBinding;

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewCompat.setLayoutDirection(mBinding.drawerLayout, ViewCompat.LAYOUT_DIRECTION_RTL);
        setNavigationDrawer();
        mBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_menu_home:
                        break;
                    case R.id.nav_menu_product_category:
                        break;
                    case R.id.nav_menu_shopping_cart:
                        break;

                    case R.id.nav_menu_latest_products:
                        break;
                    case R.id.nav_menu_most_visited_products:
                        break;

                }
                return true;
            }
        });

    }

    private void setNavigationDrawer() {
        setSupportActionBar(mBinding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
