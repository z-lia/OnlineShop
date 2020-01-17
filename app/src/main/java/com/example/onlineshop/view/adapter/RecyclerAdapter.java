package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ListItemProductImageBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.HomeMainActivity;
import com.example.onlineshop.view.ProductDetailActivity;
import com.example.onlineshop.view.ProductListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductHolder> {

    private List<Product> productList;
    private Context mContext;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public RecyclerAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        mContext = context;

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view ;
        //view = inflater.inflate(R.layout.list_item_product, parent, false);
        if (mContext instanceof HomeMainActivity)
            view = inflater.inflate(R.layout.list_item_product, parent, false);
        else //if (mContext instanceof ProductListActivity)
            view = inflater.inflate(R.layout.list_item_product_all, parent, false);
        return new ProductHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        private Product mProduct;
        private Context mContext;
        private ImageView imageViewProduct;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewPriceOnSale;

        public ProductHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.image_view_product);
            textViewName = itemView.findViewById(R.id.text_view_product_name);
            textViewPrice = itemView.findViewById(R.id.text_view_product_price);
            textViewPriceOnSale = itemView.findViewById(R.id.text_view_product_price_on_sale);
            mContext = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = ProductDetailActivity.newIntent(mContext, mProduct.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Product product) {
            mProduct = product;
            textViewName.setText(mProduct.getName());
            textViewPrice.setText(mProduct.getPrice());
            if (mProduct.isOnSale()) {
                textViewPrice.setPaintFlags(textViewPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                textViewPriceOnSale.setText(mProduct.getSalePrice());
            }
            Picasso.get()
                    .load(mProduct.getImages().get(0).getSrc())
                    .into(imageViewProduct);
        }


    }
}
