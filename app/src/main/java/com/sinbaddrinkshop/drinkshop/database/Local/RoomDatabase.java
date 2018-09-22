package com.sinbaddrinkshop.drinkshop.database.Local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

@Database(entities = {Cart.class}, version = 1)

public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract FavoriteDAO favoriteDAO();

    public static RoomDatabase instance;

    public static RoomDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, RoomDatabase.class, "Drink")
                    .allowMainThreadQueries()
                    .build();

        return instance;
    }
}
