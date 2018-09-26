package com.sinbaddrinkshop.drinkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sinbaddrinkshop.drinkshop.Adapter.DrinkAdapter;
import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    List<String> suggestionList = new ArrayList<>();

    List<Drink> loadData = new ArrayList<>();

    APIService mApiService;

    CompositeDisposable compositeDisposable;


    MaterialSearchBar materialsearchbar;
    RecyclerView recycler_search;

    DrinkAdapter searchAdapter, adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        materialsearchbar = (MaterialSearchBar) findViewById(R.id.searchBar);
        materialsearchbar.setHint("Enter Your Item");

        recycler_search = (RecyclerView) findViewById(R.id.recycler_search);
        recycler_search.setLayoutManager(new GridLayoutManager(this, 2));


        compositeDisposable = new CompositeDisposable();

        mApiService = Common.getApiService();

        loadItem();


        materialsearchbar.setCardViewElevation(10);
        materialsearchbar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<String> suggest = new ArrayList<>();
                for (String search : suggestionList) {
                    if (search.toLowerCase().contains(materialsearchbar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialsearchbar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialsearchbar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recycler_search.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                startSearch(text);

            }

            @Override
            public void onButtonClicked(int buttonCode) {


            }
        });

    }

    private void startSearch(CharSequence text) {
        List<Drink> result = new ArrayList<>();
        for (Drink drink : loadData)
            if (drink.getName().contains(text))
                result.add(drink);
        searchAdapter = new DrinkAdapter(this, result);
        recycler_search.setAdapter(searchAdapter);
    }

    private void loadItem() {
        compositeDisposable.add(mApiService.getSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Drink>>() {
                    @Override
                    public void accept(List<Drink> drinks) throws Exception {

                        searchItem(drinks);
                        searchSuggestion(drinks);
                    }
                }));
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void searchSuggestion(List<Drink> drinks) {
        for (Drink drink : drinks)
            suggestionList.add(drink.getName());
        materialsearchbar.setLastSuggestions(suggestionList);
    }

    private void searchItem(List<Drink> drinks) {
        loadData = drinks;

        adapter = new DrinkAdapter(this, drinks);
        recycler_search.setAdapter(adapter);
    }
}
