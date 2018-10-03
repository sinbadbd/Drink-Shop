package com.sinbaddrinkshop.drinkshop.database.Local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CartDAO {

    @Query("SELECT * FROM Cart")
    Flowable<List<Cart>> getCartItems();

    @Query("SELECT * FROM CART WHERE id=:cartItemId")
    Flowable<List<Cart>> getCartItemsById(int cartItemId);


    @Query("SELECT COUNT(*) FROM Cart")
    int countCartItem();

    @Query("SELECT sum(price) FROM Cart")
    float cartPrice();

    @Query("DELETE FROM Cart")
    void emptyCart();

    @Insert
    void insertCart(Cart... carts);

    @Update
    void updateCart(Cart... carts);

    @Update
    void deleteCartByItem(Cart... carts);

}
