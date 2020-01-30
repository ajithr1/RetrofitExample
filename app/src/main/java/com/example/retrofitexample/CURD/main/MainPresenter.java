package com.example.retrofitexample.CURD.main;

import android.util.Log;

import com.example.retrofitexample.CURD.Model.Note;
import com.example.retrofitexample.CURD.api.ApiClientCurd;
import com.example.retrofitexample.CURD.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.retrofitexample.CURD.main.MainActivity.TAG;

class MainPresenter {

    private MainView view;

    MainPresenter(MainView view) {
        this.view = view;
    }

    void getData() {

        view.showLoading();
        Log.d(TAG, "getData: Presenter");

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getAllNotes();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                    Log.d(TAG, "onResponse: Presenter"+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                view.hideLoading();
                Log.d(TAG, "onFailure: Presenter");
                view.onErrorLoading(t.getMessage());
            }
        });
    }
}
