package com.example.retrofitexample.CURD.Presenter;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

public interface PresenterInterface {

    void insert(final String title, final String note, final int color);

    void update(int id, final String title, final String note, final int color);

    void delete(int id);

    void insertDb(Note note);

    void editDb(Note note);

    void deleteDb(int id);
}
