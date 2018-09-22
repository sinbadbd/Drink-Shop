package com.sinbaddrinkshop.drinkshop.database.Local;

import com.sinbaddrinkshop.drinkshop.database.DataSource.IFavoriteDataSource;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDatasource implements IFavoriteDataSource {

    private FavoriteDAO favoriteDAO;
    public static FavoriteDatasource instance;

    public FavoriteDatasource(FavoriteDAO favoriteDAO) {
        this.favoriteDAO = favoriteDAO;
    }

    public static FavoriteDatasource getInstance(FavoriteDAO favoriteDAO) {

        if (instance == null)
            instance = new FavoriteDatasource(favoriteDAO);
        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavItem() {
        return favoriteDAO.getFavItem();
    }

    @Override
    public int isFavorite(int itemId) {
        return favoriteDAO.isFavorite(itemId);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDAO.delete(favorite);
    }
}
