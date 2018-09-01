package com.sinbaddrinkshop.drinkshop.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinbaddrinkshop.drinkshop.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    ImageView image_product;
    TextView text_menu_name;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        image_product = (ImageView) itemView.findViewById(R.id.menu_image);
        text_menu_name = (TextView) itemView.findViewById(R.id.text_menu_name);
    }
}

