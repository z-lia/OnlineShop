package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListItemCartBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.adapter.viewholder.CategoryHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartHolder> {

    private List<Product> mProductList;
    private Context mContext;

    public void setProductList(List<Product> mProductList) {
        this.mProductList = mProductList;
    }

    public CartRecyclerAdapter(List<Product> productList, Context context) {
        this.mProductList = productList;
        mContext = context;
    }


    @NonNull
    @Override
    public CartRecyclerAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCartBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.list_item_cart, parent, false);
        return new CartHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerAdapter.CartHolder holder, int position) {
        holder.bind(mProductList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private Product mProduct;
        private ListItemCartBinding mCartItemBinding;

        public CartHolder(ListItemCartBinding binding) {
            super(binding.getRoot());
            mCartItemBinding = binding;

        }

        public void bind(Product product) {
            mProduct = product;
            mCartItemBinding.textViewProductName.setText(mProduct.getName());
//            mCartItemBinding.setText(mProduct.getPrice());
//            if (mProduct.isOnSale()) {
//                mCartItemBinding.textViewSalePrice.setText(String.valueOf(mProduct.getPrice() - mProduct.getSalePrice());
//                textViewPriceOnSale.setText(mProduct.getSalePrice());
//            }
//            Picasso.get()
//                    .load(mProduct.getImages().get(0).getSrc())
//                    .into(imageViewProduct);
        }


    }
}

