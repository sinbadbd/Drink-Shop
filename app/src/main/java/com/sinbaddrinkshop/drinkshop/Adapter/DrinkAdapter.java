package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.Retrofit.ItemClickListener;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {


    Context context;
    List<Drink> drinks;

    public DrinkAdapter(Context context, List<Drink> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemVie = LayoutInflater.from(context).inflate(R.layout.drink_layout_item, null);
        return new DrinkViewHolder(itemVie);
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder holder, int position) {


        holder.text_price.setText(new StringBuilder("TK").append(drinks.get(position).getPrice()));
        holder.text_drink_name.setText(drinks.get(position).getName());

        Picasso.with(context)
                .load(drinks.get(position).getLink()).into(holder.menu_image);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Common.ToastMessage(context, "Click");
            }
        });


    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
