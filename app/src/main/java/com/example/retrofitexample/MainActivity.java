package com.example.retrofitexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitexample.CURD.EditorActivity;
import com.example.retrofitexample.Control.JsonPlaceHolderApi;
import com.example.retrofitexample.Control.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private Switch aSwitch;
    private FloatingActionButton fab;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    public static final String TAG = "ajju";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.code);
        aSwitch = findViewById(R.id.switch1);
        fab = findViewById(R.id.floatingActionButton);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thub-api.smartron.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Log.d(TAG, "onCreate: " + jsonPlaceHolderApi.toString());

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()){
                    textViewResult.setText("");
                    controlOn();
                }else {
                    textViewResult.setText("");
                    controlOff();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
    }

    private void controlOn() {

        JsonObject jsonObject = null;

        try {

            String js = "{'things':[{'thing_id':'ZB5526146540340438','entity_id':'1','property_name':'OnOff','property_type':'SWITCH','property_value':'1'}]}";

            JsonParser jsonParser = new JsonParser();

            jsonObject = jsonParser.parse(js).getAsJsonObject();

            Log.d(TAG, "controlOn: " + jsonObject.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        Call<Post> call = jsonPlaceHolderApi.turnOn(jsonObject);

        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d(TAG, "onResponse: ");

                textViewResult.setText(""+response.code());
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

                textViewResult.setText(""+t.getMessage());
            }
        });
    }

    private void controlOff() {

        JsonObject jsonObject = null;

        try {

            String js = "{'things':[{'thing_id':'ZB5526146540340438','entity_id':'1','property_name':'OnOff','property_type':'SWITCH','property_value':'0'}]}";

            JsonParser jsonParser = new JsonParser();

            jsonObject = jsonParser.parse(js).getAsJsonObject();

            Log.d(TAG, "controlOn: " + jsonObject.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        Call<Post> call = jsonPlaceHolderApi.turnOn(jsonObject);

        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d(TAG, "onResponse: ");

                textViewResult.setText(""+response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

                textViewResult.setText(""+t.getMessage());
            }
        });
    }
}
