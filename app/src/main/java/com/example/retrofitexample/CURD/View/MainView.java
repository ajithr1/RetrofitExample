package com.example.retrofitexample.CURD.View;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

public interface MainView {

    void insert(Note note);

    void edit(Note note);

    void delete(int id);
}
