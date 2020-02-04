package com.example.retrofitexample.CURD.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;
import com.example.retrofitexample.CURD.Presenter.PresenterInterface;
import com.example.retrofitexample.CURD.SQLite.MessageRepository;
import com.example.retrofitexample.CURD.SQLite.MessageViewModel;
import com.example.retrofitexample.Control.ApiClientSwitchControl;
import com.example.retrofitexample.Control.JsonPlaceHolderApi;
import com.example.retrofitexample.Control.Post;
import com.example.retrofitexample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView{

    private TextView textViewResult;
    private Switch aSwitch;

    public static final String TAG = "ajju";

    PresenterInterface presenter;
    MainAdapter adapter;
    MessageRepository repository;

    List<Note> notes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");

        textViewResult = findViewById(R.id.code);
        aSwitch = findViewById(R.id.switch1);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = findViewById(R.id.recycle);

        notes = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this, notes, listener);
        recyclerView.setAdapter(adapter);

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

        MessageViewModel messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        messageViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> messages) {
                adapter.setNotes(messages);
            }
        });
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

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

                textViewResult.setText(""+t.getMessage());
            }
        });
    }

    @Override
    public void insert(Note note) {
        repository = new MessageRepository(getApplication());
        Log.d(TAG, "insert: MainActivity SQLite"+note);
        repository.insert(note);
    }

    @Override
    public void edit(Note note) {
        repository = new MessageRepository(getApplication());
        repository.edit(note);
    }

    @Override
    public void delete(int id) {
        repository = new MessageRepository(getApplication());
        repository.delete(id);
    }

    private MainAdapter.RecyclerViewAdapter.ItemClickListener listener = new MainAdapter.RecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Note note, int position) {

            int id = note.getId();
            String title = note.getTitle();
            String message = note.getNote();
            int color = note.getColor();

            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.putExtra("color", color);

            startActivity(intent);
        }
    };
}
