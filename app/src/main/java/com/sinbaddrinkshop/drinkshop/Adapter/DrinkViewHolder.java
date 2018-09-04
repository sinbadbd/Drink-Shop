package com.sinbaddrinkshop.drinkshop.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.Retrofit.ItemClickListener;

public class DrinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

      TextView text_drink_name, text_price;
      ImageView menu_image;


    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DrinkViewHolder(View itemView) {
        super(itemView);

        menu_image = (ImageView) itemView.findViewById(R.id.menu_image);
        text_drink_name = (TextView) itemView.findViewById(R.id.text_drink_name);
        text_price = (TextView) itemView.findViewById(R.id.text_price);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
