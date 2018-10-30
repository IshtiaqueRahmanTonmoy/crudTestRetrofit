package com.example.my_pc.retrofittest.retrofit;

import com.example.my_pc.retrofittest.model.Person;
import com.example.my_pc.retrofittest.model.PopUpItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by my_pc on 3/8/2018.
 */

public interface RetrofitService {

    @GET("myapi.php/")
    Call<List<Person>> getAllPerson();

    @Headers("token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJUb2tlbklkIjowLCJVc2VySWQiOjIwNTAsIlVzZXJUeXBlSWQiOjEsIkV4cGlyZURhdGUiOiIyMDE4LTEwLTI1IDE3OjU6NDEgUE0gIiwiUmFuZG9tVmFsdWUiOiJjZjgzNjVhZS1jZjlmLTQ3NGYtYThiYS0yYTdkMjBjMzAzM2IifQ.f6OLivFUOw-W_fZySXh9XhDdKyAxm5YMgBw-5LGPae8")
    @GET("Api/ApiMasterCategory/GetMasterCategoryListByBrandId/{Id}")
    Call<List<PopUpItem>> getList(@Path("id") int Id);

    @POST("myapi.php/")
    Call<Person> addPerson(@Body Person person);

    @PUT("myapi.php/{id}")
    Call<Person> updatePerson(@Path("id") int id, @Body Person person);

    @PATCH("myapi.php/")   //-- This for delete
    Call<Person> delete(@Body Person person);

    @POST("get_data.php/")  //--This for delete
    Call<Person> deletePerson(@Body Person person);
}
