package com.sinbaddrinkshop.drinkshop.utils;

import com.sinbaddrinkshop.drinkshop.Retrofit.IDrinkShopAPI;
import com.sinbaddrinkshop.drinkshop.Retrofit.RetrofitClient;

public class Common {

   public static final String BASE_URL = "http://api.homessolutionbd.com/drinkapi/";
    //public static final String BASE_URL = "http://192.168.0.100/drinkapi/";

    public static IDrinkShopAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }
}
