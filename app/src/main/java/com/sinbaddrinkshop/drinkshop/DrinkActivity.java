package com.sinbaddrinkshop.drinkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sinbaddrinkshop.drinkshop.Adapter.DrinkAdapter;
import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DrinkActivity extends AppCompatActivity {

    APIService mApiService;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        mApiService = Common.getApiService();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_drink);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        TextView textView = (TextView) findViewById(R.id.text_menu_name);
        textView.setText(Common.currentCategory.name);

        loadListDrink(Common.currentCategory.id);

    }

    private void loadListDrink(int menuId) {

        compositeDisposable.add(mApiService.getDrinkById(menuId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Drink>>() {
                    @Override
                    public void accept(List<Drink> drinks) throws Exception {
                        displayDrinkList(drinks);
                    }
                }));
    }

    private void displayDrinkList(List<Drink> drinks) {
        DrinkAdapter drinkAdapter = new DrinkAdapter(this, drinks);
        recyclerView.setAdapter(drinkAdapter);
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();


    }
}
