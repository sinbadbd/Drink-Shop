package com.sinbaddrinkshop.drinkshop.database.DataSource;

import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {

    Flowable<List<Cart>> getCartItems();

    Flowable<List<Cart>> getCartItemsById(int cartItemId);

    int countCartItem();

    float cartPrice();

    void emptyCart();

    void insertCart(Cart... carts);

    void updateCart(Cart... carts);

    void deleteCartByItem(Cart... carts);
}
