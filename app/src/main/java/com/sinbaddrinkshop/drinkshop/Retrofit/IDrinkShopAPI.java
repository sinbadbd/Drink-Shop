package com.sinbaddrinkshop.drinkshop.Retrofit;

import com.sinbaddrinkshop.drinkshop.Model.CheckUserResponse;
import com.sinbaddrinkshop.drinkshop.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IDrinkShopAPI {
    @Headers({
            "Content-Type: Application/json",
            "User-Agent: Your-App-Name"
    })
    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse> checkUserExist(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("register.php")
    Call<User> registerNameUser(@Field("phone") String phone,
                                @Field("name") String name,
                                @Field("birthday") String birthday,
                                @Field("address") String address);
}
