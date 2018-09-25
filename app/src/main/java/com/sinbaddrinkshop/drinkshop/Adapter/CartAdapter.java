package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.database.Model.Cart;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Cart> carts;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {

        Picasso.with(context)
                .load(carts.get(position).link)
                .into(holder.img_product);
        holder.txt_amount.setNumber(String.valueOf(carts.get(position).amount));
        holder.txt_price.setText(new StringBuilder("$").append(carts.get(position).price));
        holder.txt_product_name.setText(carts.get(position).name);
        holder.txt_sugar_ice.setText(new StringBuilder("Sugar: ").append(carts.get(position).sugar)
                .append("%").append("\n").append("Ice: ").append(carts.get(position).ice).append("%").toString());

        holder.txt_amount.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Cart cart = carts.get(position);
                cart.amount = String.valueOf(newValue);
                Common.cartRepository.updateCart(cart);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }


    // View Holder
    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product;
        TextView txt_product_name, txt_sugar_ice, txt_price;
        ElegantNumberButton txt_amount;

        public RelativeLayout view_background;
        public LinearLayout view_foreground;

        public CartViewHolder(View itemView) {
            super(itemView);
            img_product = (ImageView) itemView.findViewById(R.id.img_product);
            txt_product_name = (TextView) itemView.findViewById(R.id.txt_product_name);
            txt_sugar_ice = (TextView) itemView.findViewById(R.id.txt_sugar_ice);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            txt_amount = (ElegantNumberButton) itemView.findViewById(R.id.txt_amount);


            view_background = (RelativeLayout) itemView.findViewById(R.id.view_background);
            view_foreground = (LinearLayout) itemView.findViewById(R.id.view_foreground);

        }
    }

    public void removeItem(int position) {
        carts.remove(position);
        notifyItemRemoved(position);
    }


    public void restoreItem(Cart item, int position) {

        carts.add(position, item);
        notifyItemInserted(position);
    }
}
