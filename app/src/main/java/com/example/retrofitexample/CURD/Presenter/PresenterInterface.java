package com.example.retrofitexample.CURD.Presenter;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

import java.util.List;

public interface PresenterInterface {

    void insert(final String title, final String note, final int color);

    void update(int id, final String title, final String note, final int color);

    void delete(int id);

    void getData();

    void onGetResult(List<Note> notes);

    void onErrorLoading(String message);
}
