package com.example.retrofitexample.CURD.Presenter;

import android.util.Log;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;
import com.example.retrofitexample.CURD.ModelRetrofit.RetrofitDb;
import com.example.retrofitexample.CURD.ModelRetrofit.RetrofitDbRepository;
import com.example.retrofitexample.CURD.View.EditorView;
import com.example.retrofitexample.CURD.View.MainActivity;
import com.example.retrofitexample.CURD.View.MainView;

import static com.example.retrofitexample.CURD.View.MainActivity.TAG;

public class Presenter implements PresenterInterface {

    private MainView mainView;
    private EditorView editorView;

    public Presenter(MainView view) {
        mainView = view;
    }

    public Presenter(EditorView editorView) {
        this.editorView = editorView;
    }

    private RetrofitDb retrofitDb;

    @Override
    public void insert(String title, String note, int color) {
        retrofitDb = new RetrofitDbRepository(editorView);
        retrofitDb.insertInToRetrofit(title, note, color);
    }

    @Override
    public void update(int id, String title, String note, int color) {
        retrofitDb = new RetrofitDbRepository(editorView);
        retrofitDb.editInRetrofit(id, title, note, color);
    }

    @Override
    public void delete(int id) {
        retrofitDb = new RetrofitDbRepository(editorView);
        retrofitDb.deleteFromRetrofit(id);
    }

    @Override
    public void insertDb(Note note) {
        mainView = new MainActivity();
        Log.d(TAG, "insertDb: "+note);
        mainView.insert(note);
    }

    @Override
    public void editDb(Note note) {
        mainView = new MainActivity();
        mainView.edit(note);
    }

    @Override
    public void deleteDb(int id) {
        mainView = new MainActivity();
        mainView.delete(id);
    }

}
