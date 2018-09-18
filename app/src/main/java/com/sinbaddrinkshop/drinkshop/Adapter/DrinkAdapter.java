package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context).inflate(R.layout.add_to_cart_layout, null);

        ImageView img_cart_product = (ImageView) itemView.findViewById(R.id.img_cart_product);
        TextView txt_cart_product_name = (TextView) itemView.findViewById(R.id.txt_cart_product_name);
        final ElegantNumberButton txt_count = (ElegantNumberButton) itemView.findViewById(R.id.txt_count);

        final EditText comments = (EditText) itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM = (RadioButton) itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL = (RadioButton) itemView.findViewById(R.id.rdi_sizeL);

        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sizeOfCup = 0;
            }
        });

        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sizeOfCup = 1;
            }
        });


        RadioButton rdi_sugar_100 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_100);
        RadioButton rdi_sugar_70 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_70);
        RadioButton rdi_sugar_50 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_50);
        RadioButton rdi_sugar_30 = (RadioButton) itemView.findViewById(R.id.rdi_sugar_30);
        RadioButton rdi_sugar_free = (RadioButton) itemView.findViewById(R.id.rdi_sugar_free);


        rdi_sugar_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 30;
            }
        });
        rdi_sugar_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 50;
            }
        });
        rdi_sugar_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 70;
            }
        });
        rdi_sugar_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 100;
            }
        });

        rdi_sugar_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 0;
            }
        });


        RadioButton rdi_ice_100 = (RadioButton) itemView.findViewById(R.id.rdi_ice_100);
        RadioButton rdi_ice_70 = (RadioButton) itemView.findViewById(R.id.rdi_ice_70);
        RadioButton rdi_ice_30 = (RadioButton) itemView.findViewById(R.id.rdi_ice_30);
        RadioButton rdi_ice_free = (RadioButton) itemView.findViewById(R.id.rdi_ice_free);


        rdi_ice_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 30;
            }
        });
        rdi_ice_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 70;
            }
        });
        rdi_ice_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 100;
            }
        });
        rdi_ice_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.suger = 0;
            }
        });

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
//
//                if (Common.sizeOfCup == -1) {
//                    Common.ToastMessage(context, "Please Choose size of Cup");
//                    return;
//                }
//                if (Common.ice == -1) {
//                    Common.ToastMessage(context, "Please Choose size of Ice");
//                    return;
//                }
//                if (Common.suger == -1) {
//                    Common.ToastMessage(context, "Please Choose size of Sugar");
//                    return;
//                }

                showConfirmDialog(position, txt_count.getNumber());
                dialog.dismiss();

            }
        });

        builder.show();
    }

    private void showConfirmDialog(final int position, final String number) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.confirm_add_to_cart_layout, null);

        ImageView img_product = (ImageView) view.findViewById(R.id.img_product);
        TextView txt_cart_product_name = (TextView) view.findViewById(R.id.txt_cart_product_name);
        TextView txt_cart_product_price = (TextView) view.findViewById(R.id.txt_cart_product_price);
        TextView txt_sugar = (TextView) view.findViewById(R.id.txt_sugar);
        TextView txt_ice = (TextView) view.findViewById(R.id.txt_ice);
        TextView txt_topping_extra = (TextView) view.findViewById(R.id.txt_topping_extra);

        Picasso.with(context).load(drinks.get(position).getLink()).into(img_product);
        txt_cart_product_name.setText(new StringBuilder(drinks.get(position).getName()));

        txt_sugar.setText(new StringBuilder("Sugar").append(Common.suger).append("%").toString());
        txt_ice.setText(new StringBuilder("Ice: ").append(Common.ice).append("%").toString());

        double price = (drinks.get(position).getPrice() * Double.parseDouble(number)) + Common.topping;
        txt_cart_product_price.setText(new StringBuilder("$").append(price));

        if (Common.sizeOfCup == 1)
            price += 3.0;

        txt_cart_product_price.setText(new StringBuilder("$").append(price));

        StringBuilder tapping_final_comment = new StringBuilder("");
        for (String line : Common.toppingAddress)
            tapping_final_comment.append(line).append("\n");

        txt_topping_extra.setText(tapping_final_comment);

        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });


        builder.setView(view);
        builder.show();
    }


    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
