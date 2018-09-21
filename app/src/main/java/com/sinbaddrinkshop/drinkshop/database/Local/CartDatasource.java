package com.sinbaddrinkshop.drinkshop.database.Local;

import com.sinbaddrinkshop.drinkshop.database.DataSource.ICartDataSource;
import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartDatasource implements ICartDataSource {

    private CartDAO cartDAO;
    private static CartDatasource instance;

    public CartDatasource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public static CartDatasource getInstance(CartDAO cartDAO) {

        if (instance == null)
            instance = new CartDatasource(cartDAO);
        return instance;
    }


    @Override
    public Flowable<List<Cart>> getCartItems() {
        return cartDAO.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getCartItemsById(int cartItemId) {
        return cartDAO.getCartItemsById(cartItemId);
    }

    @Override
    public int countCartItem() {
        return cartDAO.countCartItem();
    }

    @Override
    public void emptyCart() {

    }

    @Override
    public void insertCart(Cart... carts) {
        cartDAO.insertCart(carts);
    }

    @Override
    public void updateCart(Cart... carts) {
        cartDAO.updateCart(carts);
    }

    @Override
    public void deleteCartByItem(Cart... carts) {

        cartDAO.deleteCartByItem(carts );
    }
}
