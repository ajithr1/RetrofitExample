package com.example.retrofitexample.Control;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientSwitchControl {

    private static final String BASE_URL = "https://thub-api.smartron.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
