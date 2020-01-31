package com.example.retrofitexample.CURD.ModelRetrofit;

import android.util.Log;

import com.example.retrofitexample.CURD.Presenter.Presenter;
import com.example.retrofitexample.CURD.Presenter.PresenterInterface;
import com.example.retrofitexample.CURD.View.EditorView;
import com.example.retrofitexample.CURD.View.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.retrofitexample.CURD.View.MainActivity.TAG;

public class RetrofitDbRepository implements RetrofitDb {

    private PresenterInterface presenterInterface;
    private MainView view;
    private EditorView editorView;

    public RetrofitDbRepository(MainView view) {
        this.view = view;
    }

    public RetrofitDbRepository(EditorView editorView) {
        this.editorView = editorView;
    }

    @Override
    public void insertInToRetrofit(String title, final String note, int color) {

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);

        Call<Note> call = apiInterface.saveNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {

                if (response.isSuccessful() && response.body() != null) {

                    boolean success = response.body().getSuccess();

                    if (success) {
                        Log.d(TAG, "insert into SQLite");

                    } else {
                        Log.d(TAG, "insert into SQLite failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d(TAG, "insert into Retrofit failed");
            }
        });
    }

    @Override
    public void editInRetrofit(int id, String title, String note, int color) {

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);

        Call<Note> call = apiInterface.updateNote(id, title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {

                if (response.isSuccessful() && response.body() != null) {


                    boolean success = response.body().getSuccess();

                    if (success) {
                        Log.d(TAG, "edit in SQLite");

                    } else {
                        Log.d(TAG, "edit in SQLite failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d(TAG, "edit in Retrofit failed");
            }
        });

    }

    @Override
    public void deleteFromRetrofit(int id) {

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {

                if (response.isSuccessful() && response.body() != null){

                    boolean success = response.body().getSuccess();

                    if (success) {
                        Log.d(TAG, "deleted");

                    } else {
                        Log.d(TAG, "delete SQLite failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Log.d(TAG, "delete failed");
            }
        });

    }

    @Override
    public void getAllFromRetrofit() {

        presenterInterface = new Presenter(view);

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getAllNotes();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: Db"+response.body());
                    presenterInterface.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Log.d(TAG, "onFailure: Presenter");
                presenterInterface.onErrorLoading(t.getMessage());
            }
        });
    }
}
