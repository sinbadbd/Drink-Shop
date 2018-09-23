package com.sinbaddrinkshop.drinkshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sinbaddrinkshop.drinkshop.Adapter.FavoriteListAdapter;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListActivity extends AppCompatActivity {
    RecyclerView recycler_cart;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);



        recycler_cart = (RecyclerView) findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        recycler_cart.setHasFixedSize(true);

        compositeDisposable = new CompositeDisposable();

        cartFavoriteList();
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
        FavoriteListAdapter favoriteListAdapter = new FavoriteListAdapter(this,favorites);
        recycler_cart.setAdapter(favoriteListAdapter);
    }

}
