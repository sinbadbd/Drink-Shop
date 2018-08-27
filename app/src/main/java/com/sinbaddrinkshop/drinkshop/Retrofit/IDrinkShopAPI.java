package com.sinbaddrinkshop.drinkshop.Retrofit;

import com.sinbaddrinkshop.drinkshop.Model.CheckUserResponse;
import com.sinbaddrinkshop.drinkshop.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IDrinkShopAPI {

    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse> checkUserExists(@Field("phone") String phone);

    @POST("register.php")
    Call<User> registerNewUser(@Field("phone") String phone,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("birthday") String birthday);
}
