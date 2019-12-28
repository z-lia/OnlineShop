package com.example.onlineshop.view;

import androidx.fragment.app.Fragment;

public class HomeMainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }

}
