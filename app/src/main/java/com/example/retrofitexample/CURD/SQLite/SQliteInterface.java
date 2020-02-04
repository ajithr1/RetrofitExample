package com.example.retrofitexample.CURD.SQLite;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

public interface SQliteInterface {

    void insert(Note note);

    void edit(Note note);

    void delete(int id);
}
