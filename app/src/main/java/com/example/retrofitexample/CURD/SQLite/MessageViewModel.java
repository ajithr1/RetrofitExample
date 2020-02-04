package com.example.retrofitexample.CURD.SQLite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private MessageRepository repository;
    private LiveData<List<Note>> allMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);

        repository = new MessageRepository(application);
        allMessages = repository.getAllMessages();
    }

    public void insert(Note note){
        repository.insert(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allMessages;
    }
}
