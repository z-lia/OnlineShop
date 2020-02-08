package com.example.onlineshop.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;
import com.example.onlineshop.network.WooCommerceRepository;
import com.example.onlineshop.utils.NetworkHelper;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectionFragment extends Fragment implements WooCommerceRepository.NetworkErrorCallBack {


    public ConnectionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NetworkHelper.isConnected(getActivity())) {
            Intent intent = NoInternetActivity.newIntent(getActivity());
            startActivity(intent);
            getActivity().finish();
        }


        WooCommerceRepository.getsInstance().setNetworkErrorCallBack(this);
    }


    @Override
    public void onFail(String errorMessage) {


//        new AlertDialog.Builder(getActivity())
//                .setMessage(errorMessage)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                       dialogInterface.dismiss();
//                    }
//                })
//
//                .setView(this.getView())
//                .create().show();


        Snackbar snackbar = Snackbar
                .make(this.getView(), "" + errorMessage, Snackbar.LENGTH_LONG)
                .setAction(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
//                        snackbar1.show();
                    }
                });
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;

        // calculate actionbar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getActivity().getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }


        // set margin
        params.setMargins(0, actionBarHeight, 0, 0);
        view.setLayoutParams(params);
        snackbar.show();
    }
}
