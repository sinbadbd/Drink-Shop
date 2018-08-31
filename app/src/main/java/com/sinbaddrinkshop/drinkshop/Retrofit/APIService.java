package com.sinbaddrinkshop.drinkshop.Retrofit;

import com.sinbaddrinkshop.drinkshop.Model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("gender") String gender);
}
