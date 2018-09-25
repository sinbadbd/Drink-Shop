package com.sinbaddrinkshop.drinkshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sinbaddrinkshop.drinkshop.Adapter.CartAdapter;
import com.sinbaddrinkshop.drinkshop.database.Model.Cart;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.sinbaddrinkshop.drinkshop.utils.RecyclerItemTouchHelperListener;
import com.sinbaddrinkshop.drinkshop.utils.RecylerTouchHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recycler_cart;
    Button btn_place_order;

    List<Cart> localFavorite = new ArrayList<>();

    RelativeLayout rootLayout;

    CartAdapter cartAdapter;


    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        compositeDisposable = new CompositeDisposable();
        btn_place_order = (Button) findViewById(R.id.btn_place_order);
        recycler_cart = (RecyclerView) findViewById(R.id.recycler_cart);

        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        recycler_cart.setHasFixedSize(true);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ItemTouchHelper.SimpleCallback simpleCallback = new RecylerTouchHelper(0, ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler_cart);

        loadCartItem();
    }

    private void loadCartItem() {

        compositeDisposable.add(Common.cartRepository.getCartItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Cart>>() {
                    @Override
                    public void accept(List<Cart> carts) throws Exception {
                        displayCartItem(carts);
                    }
                })

        );
    }

    private void displayCartItem(List<Cart> carts) {
        localFavorite = carts;
        cartAdapter = new CartAdapter(this, carts);
        recycler_cart.setAdapter(cartAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCartItem();
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof CartAdapter.CartViewHolder) {

            String name = localFavorite.get(viewHolder.getAdapterPosition()).name;

            final Cart deleteItem = localFavorite.get(viewHolder.getAdapterPosition());

            final int deleteIndex = viewHolder.getAdapterPosition();

            cartAdapter.removeItem(deleteIndex);

            Common.cartRepository.deleteCartByItem(deleteItem);

            Snackbar snackbar = Snackbar.make(rootLayout, new StringBuilder(name)
                    .append(" Remove Item")
                    .toString(), Snackbar.LENGTH_LONG);


            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cartAdapter.restoreItem(deleteItem, deleteIndex);
                    Common.cartRepository.insertCart(deleteItem);
                }
            });

            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();


        }

    }
}
