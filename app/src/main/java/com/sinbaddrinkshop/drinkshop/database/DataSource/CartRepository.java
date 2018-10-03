package com.sinbaddrinkshop.drinkshop.database.DataSource;

import com.sinbaddrinkshop.drinkshop.database.Model.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartRepository implements ICartDataSource {

    private ICartDataSource iCartDataSource;

    public CartRepository(ICartDataSource iCartDataSource) {
        this.iCartDataSource = iCartDataSource;
    }

    private static CartRepository instance;


    public static CartRepository getInstance(ICartDataSource iCartDataSource) {
        if (instance == null)
            instance = new CartRepository(iCartDataSource);

        return instance;

    }

    @Override
    public Flowable<List<Cart>> getCartItems() {
        return iCartDataSource.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getCartItemsById(int cartItemId) {
        return iCartDataSource.getCartItemsById(cartItemId);
    }

    @Override
    public int countCartItem() {
        return iCartDataSource.countCartItem();
    }

    @Override
    public float cartPrice() {
        return iCartDataSource.cartPrice();
    }

    @Override
    public void emptyCart() {

        iCartDataSource.emptyCart();
    }

    @Override
    public void insertCart(Cart... carts) {
        iCartDataSource.insertCart(carts);
    }

    @Override
    public void updateCart(Cart... carts) {
        iCartDataSource.updateCart(carts);
    }

    @Override
    public void deleteCartByItem(Cart... carts) {

        iCartDataSource.deleteCartByItem(carts);
    }
}
