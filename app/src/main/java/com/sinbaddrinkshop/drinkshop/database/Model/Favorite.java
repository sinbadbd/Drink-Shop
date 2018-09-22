package com.sinbaddrinkshop.drinkshop.database.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Favorite")
public class Favorite {

    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "link")
    public String link;
 
    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "menuId")
    public double menuId;

}
