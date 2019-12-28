package com.example.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ProductHolder> {

    private List<Product> productList ;
    private Context mContext;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public RecyclerAdapter(List<Product> productList , Context context) {
        this.productList = productList;
        mContext = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_product ,parent ,false);
        return new ProductHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private Product mProduct;
        private Context mContext;
        private ImageView imageViewProduct;
        private TextView textViewName;
        private TextView textViewPrice;

        public ProductHolder(@NonNull View itemView , Context context) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.image_view_product);
            textViewName = itemView.findViewById(R.id.text_view_product_name);
            textViewPrice = itemView.findViewById(R.id.text_view_product_price);
        }

        public void bind(Product product){
            mProduct = product;
            textViewName.setText(mProduct.getName());
            textViewPrice.setText(mProduct.getPrice());
            Picasso.get()
                    .load(mProduct.getImages().get(0).getSrc())
                    .into(imageViewProduct);
        }
    }
}
