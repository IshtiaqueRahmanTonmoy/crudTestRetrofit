package com.example.my_pc.retrofittest.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by my_pc on 3/8/2018.
 */

public class MyRetrofit {

    private static Retrofit retrofit = null;
    // --- Set the base url
    //private static final String BASE_URL = "https://firozmahmud.000webhostapp.com/retrofit_test/";
    //private static final String BASE_URL = "http://192.168.0.113/retrofit_test/";  // for PC local host
    private static final String BASE_URLS = "http://test.akhtar3rdeye.com/";  // for PC local host

    public static Retrofit getMyRetrofit() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URLS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
