package com.example.onlineshop.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlineshop.view.CategoryListFragment;
import com.example.onlineshop.viewmodel.CategoriesViewModel;


public class CategoryPagerAdapter extends FragmentStateAdapter {
    private CategoriesViewModel mCategoriesViewModel;

    public CategoryPagerAdapter(@NonNull FragmentActivity fragmentActivity  ) {
        super(fragmentActivity);
        mCategoriesViewModel = ViewModelProviders.of(fragmentActivity).get(CategoriesViewModel.class);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        return CategoryListFragment.newInstance(mCategoriesViewModel.getParentCategoriesListLiveData().getValue().get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mCategoriesViewModel.getParentCategoriesListLiveData().getValue().size();
    }
}
