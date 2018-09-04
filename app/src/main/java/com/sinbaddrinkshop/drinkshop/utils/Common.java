package com.sinbaddrinkshop.drinkshop.utils;

import android.content.Context;
import android.widget.Toast;

import com.sinbaddrinkshop.drinkshop.Model.Category;
import com.sinbaddrinkshop.drinkshop.Retrofit.APIService;
import com.sinbaddrinkshop.drinkshop.Retrofit.RetrofitClient;

public class Common {

    public static final String BASE_URL = "http://api.homessolutionbd.com/drinkapi/v1/";


    public static Category currentCategory = null;

    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


    public static String ToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        return message;

    }
}
