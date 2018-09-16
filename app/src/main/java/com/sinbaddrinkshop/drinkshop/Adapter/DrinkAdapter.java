package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
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

        View itemView = LayoutInflater.from(context).inflate(R.layout.drink_layout_item, null);
        return new DrinkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder holder, final int position) {


        holder.text_price.setText(new StringBuilder("TK").append(drinks.get(position).getPrice()));
        holder.text_drink_name.setText(drinks.get(position).getName());

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(position);
            }
        });

        Picasso.with(context)
                .load(drinks.get(position).getLink()).into(holder.menu_image);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Common.ToastMessage(context, "Click");
            }
        });


    }

    private void showAddToCartDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context).inflate(R.layout.add_to_cart_layout, null);

        ImageView img_cart_product = (ImageView) itemView.findViewById(R.id.img_cart_product);
        TextView txt_cart_product_name = (TextView) itemView.findViewById(R.id.txt_cart_product_name);
        ElegantNumberButton elegantnumberbutton = (ElegantNumberButton) img_cart_product.findViewById(R.id.txt_count);

        EditText comments = (EditText) itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM = (RadioButton) itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL = (RadioButton) itemView.findViewById(R.id.rdi_sizeL);

        RadioButton rdi_sugar_100 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_100);
        RadioButton rdi_sugar_70 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_70);
        RadioButton rdi_sugar_50 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_50);
        RadioButton rdi_sugar_30 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_30);
        RadioButton rdi_sugar_free = (RadioButton) itemView.findViewById(R.id.rdi_sugar_free);

        RadioButton rdi_ice_100 = (RadioButton) itemView.findViewById(R.id.rdi_ice_100);
        RadioButton rdi_ice_70 = (RadioButton) itemView.findViewById(R.id.rdi_ice_70);
        RadioButton rdi_ice_30 = (RadioButton) itemView.findViewById(R.id.rdi_ice_30);
        RadioButton rdi_ice_free = (RadioButton) itemView.findViewById(R.id.rdi_ice_free);

        RecyclerView recycler_topping = (RecyclerView) itemView.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);


        MultiChoiceAdapter multiChoiceAdapter = new MultiChoiceAdapter(context, Common.ToppingList);
        recycler_topping.setAdapter(multiChoiceAdapter);


        Picasso.with(context).load(drinks.get(position).getLink())
                .into(img_cart_product);

        txt_cart_product_name.setText(drinks.get(position).getName());

        builder.setView(itemView);
        builder.setNegativeButton("Add TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Common.sizeOfCup == -1) {
                    Common.ToastMessage(context, "Please Choose size if Cup");
                    return;
                }
                if (Common.ice == -1) {
                    Common.ToastMessage(context, "Please Choose size if Ice");
                    return;
                }
                if (Common.suger == -1) {
                    Common.ToastMessage(context, "Please Choose size if Sugar");
                    return;
                }
                showConfirmDialog();
                dialog.dismiss();

            }
        });

        builder.show();
    }

    private void showConfirmDialog() {

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
