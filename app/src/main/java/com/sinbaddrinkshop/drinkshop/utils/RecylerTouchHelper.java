package com.sinbaddrinkshop.drinkshop.utils;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.sinbaddrinkshop.drinkshop.Adapter.CartAdapter;
import com.sinbaddrinkshop.drinkshop.Adapter.FavoriteListAdapter;

public class RecylerTouchHelper extends ItemTouchHelper.SimpleCallback {


    RecyclerItemTouchHelperListener listener;


    public RecylerTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);

        this.listener = listener;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        if (listener != null)
            listener.onSwipe(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (viewHolder instanceof FavoriteListAdapter.FavoriteListViewHolder) {

            View foregroundView = ((FavoriteListAdapter.FavoriteListViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().clearView(foregroundView);

        } else if (viewHolder instanceof CartAdapter.CartViewHolder) {

            View foregroundView = ((CartAdapter.CartViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().clearView(foregroundView);
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

        if (viewHolder instanceof FavoriteListAdapter.FavoriteListViewHolder) {
            View foreground = ((FavoriteListAdapter.FavoriteListViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onSelected(foreground);

        } else if (viewHolder instanceof CartAdapter.CartViewHolder) {
            View foreground = ((CartAdapter.CartViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onSelected(foreground);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (viewHolder instanceof FavoriteListAdapter.FavoriteListViewHolder) {
            View foreground = ((FavoriteListAdapter.FavoriteListViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDraw(c, recyclerView, foreground, dX, dY, actionState, isCurrentlyActive);

        } else if (viewHolder instanceof CartAdapter.CartViewHolder) {
            View foreground = ((CartAdapter.CartViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDraw(c, recyclerView, foreground, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {


        if (viewHolder instanceof FavoriteListAdapter.FavoriteListViewHolder) {
            View foreground = ((FavoriteListAdapter.FavoriteListViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foreground, dX, dY, actionState, isCurrentlyActive);

        } else if (viewHolder instanceof CartAdapter.CartViewHolder) {
            View foreground = ((CartAdapter.CartViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foreground, dX, dY, actionState, isCurrentlyActive);
        }


    }
}
