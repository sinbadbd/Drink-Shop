package com.sinbaddrinkshop.drinkshop.database.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Cart")

public class Cart {

    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "link")
    public String link;


    @ColumnInfo(name = "amount")
    public String amount;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "sugar")
    public int sugar;

    @ColumnInfo(name = "ice")
    public int ice;

    @ColumnInfo(name = "toppingExtras")
    public int toppingExtras;

}
