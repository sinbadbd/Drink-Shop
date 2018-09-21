package com.sinbaddrinkshop.drinkshop.database.Local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

@Database(entities = {Cart.class}, version = 1)

public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();

    public static CartDatabase instance;

    public static CartDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, CartDatabase.class, "Drink")
                    .allowMainThreadQueries()
                    .build();

        return instance;
    }
}
