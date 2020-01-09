package com.example.onlineshop.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlineshop.model.CategoriesItem;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private List<CategoriesItem> mItemList;


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<CategoriesItem> categoriesItemList) {
        super(fragmentManager, lifecycle);
        this.mItemList = mItemList;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<CategoriesItem> categoriesItemList) {
        super(fragmentActivity);
        this.mItemList = mItemList;
    }

    public ViewPagerAdapter(@NonNull Fragment fragment, List<CategoriesItem> categoriesItemList) {
        super(fragment);
        this.mItemList = mItemList;
    }


//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
}
