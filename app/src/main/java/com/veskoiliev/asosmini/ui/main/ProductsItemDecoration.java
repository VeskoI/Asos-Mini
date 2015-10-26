package com.veskoiliev.asosmini.ui.main;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ProductsItemDecoration extends RecyclerView.ItemDecoration {

    private int mPaddingPx;

    public ProductsItemDecoration(int paddingPx) {
        mPaddingPx = paddingPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mPaddingPx;
        outRect.right = mPaddingPx;
        outRect.bottom = mPaddingPx;

        GridLayoutManager layoutManager = ((GridLayoutManager) parent.getLayoutManager());

        // Add top mPaddingPx only to first row to will avoid double mPaddingPx between elements.
        if (parent.getChildLayoutPosition(view) < layoutManager.getSpanCount()) {
            outRect.top = 3 * mPaddingPx;
        }
    }
}
