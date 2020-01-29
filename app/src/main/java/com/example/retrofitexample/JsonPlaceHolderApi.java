package com.example.retrofitexample;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @Headers({
            "Content-Type: application/json",
            "X-DeviceId: S119300064",
            "X-HomePeer: true",
            "X-Authorization: 700dc204-7924-4eea-973e-e7ce731c0177",
            "X-AccountToken: account_tk_d2b75e6f-619d-4b62-8e58-a6265d3944ba",
            "X-requestid: ajith"
    })
    @POST("/v2/hub/thing/control")
    Call<Post> turnOn(@Body JsonObject data);

    @Headers({
            "Content-Type: application/json",
            "X-DeviceId: S119300064",
            "X-HomePeer: true",
            "X-Authorization: 700dc204-7924-4eea-973e-e7ce731c0177",
            "X-AccountToken: account_tk_d2b75e6f-619d-4b62-8e58-a6265d3944ba",
            "X-requestid: ajith"
    })
    @POST("/v2/hub/thing/control")
    Call<Post> turnOff(@Body JsonObject data);
}
