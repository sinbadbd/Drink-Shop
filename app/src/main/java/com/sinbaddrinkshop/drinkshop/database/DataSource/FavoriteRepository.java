package com.sinbaddrinkshop.drinkshop.database.DataSource;

import com.sinbaddrinkshop.drinkshop.database.Local.FavoriteDatasource;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDataSource {

    private IFavoriteDataSource iFavoriteDataSource;
    private static FavoriteRepository instance;


    public FavoriteRepository(IFavoriteDataSource iFavoriteDataSource) {
        this.iFavoriteDataSource = iFavoriteDataSource;
    }


    public static FavoriteRepository getInstance(FavoriteDatasource iFavoriteDataSource) {
        if (instance == instance)
            instance = new FavoriteRepository(iFavoriteDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavItem() {
        return iFavoriteDataSource.getFavItem();
    }

    @Override
    public int isFavorite(int itemId) {
        return iFavoriteDataSource.isFavorite(itemId);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        iFavoriteDataSource.insertFav(favorites);
    }
    @Override
    public void delete(Favorite favorite) {
        iFavoriteDataSource.delete(favorite);
    }

}
