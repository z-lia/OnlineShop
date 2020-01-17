package com.example.onlineshop.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentNoInternetBinding;
import com.example.onlineshop.utils.NetworkHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoInternetFragment extends Fragment {

    private FragmentNoInternetBinding mBinding;
    public static NoInternetFragment newInstance() {

        Bundle args = new Bundle();

        NoInternetFragment fragment = new NoInternetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public NoInternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_no_internet, container, false);
         mBinding.buttonTryAgain.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(NetworkHelper.isConnected(getContext()))
                     getActivity().finish();
             }
         });

        return mBinding.getRoot();
    }

}
