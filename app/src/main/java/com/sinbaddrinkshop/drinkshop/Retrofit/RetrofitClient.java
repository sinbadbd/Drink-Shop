package com.sinbaddrinkshop.drinkshop.Retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
   // String BASE_URL = "http://api.homessolutionbd.com/drinkapi/";
    private static Retrofit retrofit = null;

//    public Retrofit getRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }

    public static Retrofit getClient(String baseURL) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
