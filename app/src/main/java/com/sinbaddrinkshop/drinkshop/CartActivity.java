package com.sinbaddrinkshop.drinkshop;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.sinbaddrinkshop.drinkshop.Adapter.CartAdapter;
import com.sinbaddrinkshop.drinkshop.Model.Order;
import com.sinbaddrinkshop.drinkshop.Model.User;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recycler_cart;
    Button btn_place_order;

    List<Cart> localFavorite = new ArrayList<>();

    RelativeLayout rootLayout;

    CartAdapter cartAdapter;


    APIService apiService;


    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        apiService = Common.getApiService();
        compositeDisposable = new CompositeDisposable();


        btn_place_order = (Button) findViewById(R.id.btn_place_order);
        recycler_cart = (RecyclerView) findViewById(R.id.recycler_cart);

        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        recycler_cart.setHasFixedSize(true);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderPlaced();
            }
        });


        ItemTouchHelper.SimpleCallback simpleCallback = new RecylerTouchHelper(0, ItemTouchHelper.LEFT, this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler_cart);

        loadCartItem();
    }

    private void orderPlaced() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Submit Order");

        final EditText edt_comment, edt_other_address;
        RadioButton rdi_user_address, rdi_other_address;

        View view = LayoutInflater.from(this).inflate(R.layout.submit_order_layout, null);

        edt_comment = (EditText) view.findViewById(R.id.edt_comment);
        edt_other_address = (EditText) view.findViewById(R.id.edt_other_address);

        rdi_user_address = (RadioButton) view.findViewById(R.id.rdi_user_address);
        rdi_other_address = (RadioButton) view.findViewById(R.id.rdi_other_address);

//        rdi_user_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    edt_other_address.setEnabled(true);
//            }
//        });
//
//        rdi_other_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                edt_comment.setEnabled(true);
//            }
//        });


        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String oderComment = edt_comment.getText().toString();
                final String address = edt_other_address.getText().toString();
                //String orderAddress;

                compositeDisposable.add(Common.cartRepository.getCartItems()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Cart>>() {
                            @Override
                            public void accept(List<Cart> carts) throws Exception {
                                sendOrderToServer(Common.cartRepository.cartPrice(), carts, oderComment, address);
//                                if (!TextUtils.isEmpty(oderComment) && !TextUtils.isEmpty(address)) {
//                                    sendOrderToServer(Common.cartRepository.cartPrice(), carts, oderComment, address);
//                                } else {
//                                    Common.ToastMessage(getApplicationContext(), "Field Required");
//                                }
                            }
                        }));

            }
        });

        builder.setView(view);
        builder.show();

    }

    private void sendOrderToServer(float price, List<Cart> carts, String oderComment, String address) {

        if (carts.size() > 0) {
            String orderDetails = new Gson().toJson(carts);


            User user = new User();

            apiService.submitOrder(price, orderDetails, oderComment, address, "haha@gmail.com")
                    .enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {

                            Log.d("order", response.body().toString());
                            Common.ToastMessage(getApplicationContext(), "Order Submit");
                            Common.cartRepository.emptyCart();
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Log.d("order", t.getMessage());
                            Common.ToastMessage(getApplicationContext(), t.getMessage());
                        }
                    });
        }

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

    //samsung321
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
