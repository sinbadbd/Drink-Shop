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

    @Query("SELECT * FROM CART")
    Flowable<List<Cart>> getCartItems();

    @Query("SELECT * FROM CART WHERE id=:cartItemId")
    Flowable<List<Cart>> getCartItemsById(int cartItemId);


    @Query("SELECT COUNT(*) FROM CART")
    int countCartItem();


    @Query("DELETE FROM CART")
    void emptyCart();

    @Insert
    void insertCart(Cart... carts);

    @Update
    void updateCart(Cart... carts);

    @Update
    void deleteCartByItem(Cart... carts);

}
