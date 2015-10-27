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

    private ProductSelectListener mListener;
    private List<Product> mData = new ArrayList<>(INITIAL_CAPACITY);

    public ProductsAdapter(ProductSelectListener listener) {
        mListener = listener;
    }

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
        holder.productId = product.getId();
        holder.price.setText(product.getCurrentPrice());
        Picasso.with(holder.icon.getContext()).load(product.getImageUrl()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    /**
     * Use this method to REPLACE the data in the adapter.
     * It calls {@link #notifyDataSetChanged()} internally, so no extra work is required
     * from the outside.
     *
     * @param data list of Products to added to the adapter.
     */
    public void setData(@NonNull List<Product> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @Bind(R.id.product_image)
        ImageView icon;

        @Bind(R.id.product_price)
        TextView price;

        long productId;

        public ProductHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onProductSelected(productId);
        }

        @Override
        public boolean onLongClick(View v) {
            // // TODO: 10/26/2015 perhaps we need a "removeFromFavorites" action as well
            mListener.addProductToFavorites(productId);
            return true;
        }
    }
}
