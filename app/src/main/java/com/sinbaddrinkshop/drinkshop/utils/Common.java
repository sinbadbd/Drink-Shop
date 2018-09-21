package com.sinbaddrinkshop.drinkshop.utils;

import android.content.Context;
import android.widget.Toast;

import com.sinbaddrinkshop.drinkshop.Model.Category;
import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.Retrofit.RetrofitClient;
import com.sinbaddrinkshop.drinkshop.database.DataSource.CartRepository;
import com.sinbaddrinkshop.drinkshop.database.Local.CartDatabase;
import com.sinbaddrinkshop.drinkshop.database.Local.CartDatasource;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static final String BASE_URL = "http://api.homessolutionbd.com/drinkapi/v1/";


    public static final int TOPPING_ID = 1;

    public static Category currentCategory = null;

    public static List<Drink> ToppingList = new ArrayList<>();

    public static double topping = 0.0;
    public static List<String> toppingAddress = new ArrayList<>();


    public static int sizeOfCup = -1;
    public static int suger = -1;
    public static int ice = -1;


    public static CartRepository cartRepository;
    public static CartDatabase cartDatasource;


    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


    public static String ToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        return message;

    }
}
