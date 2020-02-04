package com.example.retrofitexample.CURD.View;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

import java.util.List;

public interface MainView {

    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);

    void insert(Note note);

    void edit(Note note);

    void delete(int id);
}
