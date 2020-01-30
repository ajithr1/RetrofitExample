package com.example.retrofitexample.CURD.activity;

import com.example.retrofitexample.CURD.Model.Note;
import com.example.retrofitexample.CURD.api.ApiClientCurd;
import com.example.retrofitexample.CURD.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EditorPresenter {

    private EditorView view;

    EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveNote(final String title, final String note, final int color) {
        view.showProgress();

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);

        Call<Note> call = apiInterface.saveNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){



                    boolean success = response.body().getSuccess();

                    if (success){
                        view.onAddSuccess(response.body().getNote());
                    } else {
                        view.onAddError(response.body().getNote());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getMessage());

            }
        });
    }

    void editNote(final int id, final String title, final String note, final int color) {
        view.showProgress();

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);

        Call<Note> call = apiInterface.updateNote(id, title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){



                    boolean success = response.body().getSuccess();

                    if (success){
                        view.onAddSuccess(response.body().getNote());
                    } else {
                        view.onAddError(response.body().getNote());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getMessage());

            }
        });
    }

    void deleteNote(final int id) {
        view.showProgress();

        ApiInterface apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){

                    boolean success = response.body().getSuccess();

                    if (success){
                        view.onAddSuccess(response.body().getNote());
                    } else {
                        view.onAddError(response.body().getNote());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getMessage());

            }
        });
    }
}
