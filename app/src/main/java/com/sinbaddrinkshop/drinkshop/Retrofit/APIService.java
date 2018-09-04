package com.sinbaddrinkshop.drinkshop.Retrofit;

import com.sinbaddrinkshop.drinkshop.Model.Banner;
import com.sinbaddrinkshop.drinkshop.Model.Category;
import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.Model.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("gender") String gender);

    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(@Field("email") String email,
                           @Field("password") String password);

    @FormUrlEncoded
    @GET("user/{email}")
    Call<Result> userInformation(@Field("email") String email);

    @GET("getImages")
    Observable<List<Banner>> getBanners();

    @GET("getMenu")
    Observable<List<Category>> getMenu();

    @GET("getMenuId/{menu_id}")
    Observable<List<Drink>> getDrinkById(@Path("menu_id") int menuId);

}
