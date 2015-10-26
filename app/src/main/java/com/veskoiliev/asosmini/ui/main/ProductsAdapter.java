package com.veskoiliev.asosmini.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.veskoiliev.asosmini.R;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder> {

    private static final int INITIAL_CAPACITY = 20;

    private List<Product> mData = new ArrayList<>(INITIAL_CAPACITY);

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ProductHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        if (position < 0 || position > mData.size()) {
            throw new IllegalStateException("Illegal position passed in adapter!");
        }

        Product product = mData.get(position);
        holder.price.setText(product.getCurrentPrice());
        Picasso.with(holder.icon.getContext()).load(product.getImageUrl()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(@NonNull List<Product> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    static class ProductHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.product_image)
        ImageView icon;

        @Bind(R.id.product_price)
        TextView price;

        public ProductHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
