package com.sinbaddrinkshop.drinkshop.database.DataSource;

import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {

    Flowable<List<Favorite>> getFavItem();

    int isFavorite(int itemId);

    void delete(Favorite favorite);

}
