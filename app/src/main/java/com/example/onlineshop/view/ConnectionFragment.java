package com.example.onlineshop.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;
import com.example.onlineshop.utils.NetworkHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectionFragment extends Fragment {


    public ConnectionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NetworkHelper.isConnected(getActivity())) {
            Intent intent = NoInternetActivity.newIntent(getActivity());
            startActivity(intent);
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }



}
