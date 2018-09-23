package com.sinbaddrinkshop.drinkshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.sinbaddrinkshop.drinkshop.Adapter.FavoriteListAdapter;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.sinbaddrinkshop.drinkshop.utils.RecyclerItemTouchHelperListener;
import com.sinbaddrinkshop.drinkshop.utils.RecylerTouchHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {
    RecyclerView recycler_cart;

    CompositeDisposable compositeDisposable;

    FavoriteListAdapter favoriteListAdapter;

    List<Favorite> localFavorite = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);


        recycler_cart = (RecyclerView) findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        recycler_cart.setHasFixedSize(true);

        compositeDisposable = new CompositeDisposable();

        ItemTouchHelper.SimpleCallback simpleCallback = new RecylerTouchHelper(0, ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler_cart);

        cartFavoriteList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        cartFavoriteList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    void cartFavoriteList() {
        compositeDisposable.add(Common.favoriteRepository.getFavItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Favorite>>() {
                    @Override
                    public void accept(List<Favorite> favorites) throws Exception {
                        displayCartFavoriteItem(favorites);
                    }
                }));
    }

    private void displayCartFavoriteItem(List<Favorite> favorites) {
        localFavorite = favorites;
        favoriteListAdapter = new FavoriteListAdapter(this, favorites);
        recycler_cart.setAdapter(favoriteListAdapter);
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof FavoriteListAdapter.FavoriteListViewHolder) {

            String name = localFavorite.get(viewHolder.getAdapterPosition()).name;

            Favorite deleteItem = localFavorite.get(viewHolder.getAdapterPosition());

            int deleteIndex = viewHolder.getAdapterPosition();

            favoriteListAdapter.removeItem(deleteIndex);

            Common.favoriteRepository.delete(deleteItem);

           /// Snackbar snackbar = Snackbar.make(rootLayout, new StringBuilder(name).append("Remove Item").toString())
        }

    }
}
