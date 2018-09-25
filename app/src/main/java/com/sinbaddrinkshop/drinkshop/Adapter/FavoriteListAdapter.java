package com.sinbaddrinkshop.drinkshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.database.Model.Favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder> {

    Context context;
    List<Favorite> favorites;

    public FavoriteListAdapter(Context context, List<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    @Override
    public FavoriteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View itemView = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, parent, false);
//        return new FavoriteListViewHolder(itemView);
        View itemView = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, null);
        return new FavoriteListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteListViewHolder holder, int position) {

        Picasso.with(context).load(favorites.get(position).link).into(holder.img_product);
        holder.txt_product_name.setText(favorites.get(position).name);
        holder.txt_price.setText(new StringBuilder("$").append(favorites.get(position).price).toString());

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class FavoriteListViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product;
        TextView txt_product_name, txt_price;
        public RelativeLayout view_background;
        public LinearLayout view_foreground;

        public FavoriteListViewHolder(View itemView) {
            super(itemView);
            img_product = (ImageView) itemView.findViewById(R.id.img_product);
            txt_product_name = (TextView) itemView.findViewById(R.id.txt_product_name);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            view_background = (RelativeLayout) itemView.findViewById(R.id.view_background);
            view_foreground = (LinearLayout) itemView.findViewById(R.id.view_foreground);
        }
    }


    public void removeItem(int position) {
        favorites.remove(position);
        notifyItemRemoved(position);
    }


    public void restoreItem(Favorite item, int position) {

        favorites.add(position, item);
        notifyItemInserted(position);
    }

}
