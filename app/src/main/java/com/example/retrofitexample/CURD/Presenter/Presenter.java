package com.example.retrofitexample.CURD.Presenter;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;
import com.example.retrofitexample.CURD.ModelRetrofit.RetrofitDb;
import com.example.retrofitexample.CURD.ModelRetrofit.RetrofitDbRepository;
import com.example.retrofitexample.CURD.View.EditorView;
import com.example.retrofitexample.CURD.View.MainView;

import java.util.List;

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
    public void getData() {
        retrofitDb = new RetrofitDbRepository(mainView);
        retrofitDb.getAllFromRetrofit();
    }

    @Override
    public void onGetResult(List<Note> notes) {
        mainView.onGetResult(notes);
    }

    @Override
    public void onErrorLoading(String message) {
        mainView.onErrorLoading(message);
    }

}
