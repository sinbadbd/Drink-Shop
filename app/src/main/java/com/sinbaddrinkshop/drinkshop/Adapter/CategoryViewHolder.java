package com.sinbaddrinkshop.drinkshop.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.Retrofit.ItemClickListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView image_product;
    TextView text_menu_name;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public CategoryViewHolder(View itemView) {
        super(itemView);
        image_product = (ImageView) itemView.findViewById(R.id.menu_image);
        text_menu_name = (TextView) itemView.findViewById(R.id.text_menu_name);

        itemView.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view);
    }
}

