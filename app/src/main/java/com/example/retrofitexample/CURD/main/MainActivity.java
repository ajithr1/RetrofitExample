package com.example.retrofitexample.CURD.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.CURD.Model.Note;
import com.example.retrofitexample.CURD.activity.EditorActivity;
import com.example.retrofitexample.Control.ApiClientSwitchControl;
import com.example.retrofitexample.Control.JsonPlaceHolderApi;
import com.example.retrofitexample.Control.Post;
import com.example.retrofitexample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView{

    private TextView textViewResult;
    private Switch aSwitch;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    public static final String TAG = "ajju";

    MainPresenter presenter;
    MainAdapter adapter;
    List<Note> notes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.code);
        aSwitch = findViewById(R.id.switch1);
        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    textViewResult.setText("");
                    MainActivity.this.controlOn();
                } else {
                    textViewResult.setText("");
                    MainActivity.this.controlOff();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });

        presenter = new MainPresenter(this);
        presenter.getData();
        Log.d(TAG, "onCreate: MainActivity");
    }

    private void controlOn() {

        JsonPlaceHolderApi jsonPlaceHolderApi = ApiClientSwitchControl.getRetrofit().create(JsonPlaceHolderApi.class);

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

        JsonPlaceHolderApi jsonPlaceHolderApi = ApiClientSwitchControl.getRetrofit().create(JsonPlaceHolderApi.class);

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

    @Override
    public void showLoading() {
        //refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        //refreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        Log.d(TAG, "onGetResult: MainActivity"+notes);
        adapter = new MainAdapter(this, notes, listener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {
        Log.d(TAG, "onErrorLoading: MainActivity"+message);
    }

    private MainAdapter.RecyclerViewAdapter.ItemClickListener listener = new MainAdapter.RecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Note note, int position) {
            Log.d(TAG, "onItemClick: MainActivity");
            Toast.makeText(MainActivity.this, note.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };
}
