package com.sinbaddrinkshop.drinkshop.database.DataSource;

import android.arch.persistence.room.Insert;

import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {

    Flowable<List<Favorite>> getFavItem();

    int isFavorite(int itemId);

    void insertFav(Favorite...favorites);

    void delete(Favorite favorite);

}
