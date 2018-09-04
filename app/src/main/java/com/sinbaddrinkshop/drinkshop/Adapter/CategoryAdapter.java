package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinbaddrinkshop.drinkshop.DrinkActivity;
import com.sinbaddrinkshop.drinkshop.Model.Category;
import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.Retrofit.ItemClickListener;
import com.sinbaddrinkshop.drinkshop.utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.menu_item_layout, null);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {

        //load image
        Picasso.with(context)
                .load(categories.get(position)
                        .link).into(holder.image_product);
        holder.text_menu_name.setText(categories.get(position).name);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Common.currentCategory = categories.get(position);

                context.startActivity(new Intent(context, DrinkActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
