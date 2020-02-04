package com.example.retrofitexample.CURD.SQLite;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

import java.util.List;

import static com.example.retrofitexample.CURD.View.MainActivity.TAG;

public class MessageRepository implements SQliteInterface{

    private NoteDao noteDao;
    private LiveData<List<Note>> allMessages;

    public MessageRepository(Application application) {
        MessageDatabase db = MessageDatabase.getInstance(application);
        noteDao = db.messageDao();
        allMessages = noteDao.getAllMessages();
    }

    LiveData<List<Note>> getAllMessages() {
        return allMessages;
    }

    @Override
    public void insert(Note note) {
        Log.d(TAG, "insert: MessageRepository");
        new InsertMessageAsyncTask(noteDao).execute(note);
    }

    @Override
    public void edit(Note note) {
        new EditMessageAsyncTask(noteDao).execute(note);
    }

    @Override
    public void delete(int id) {
        new DeleteMessageAsyncTask(noteDao).execute(id);
    }

    @SuppressLint("StaticFieldLeak")
    private class InsertMessageAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        InsertMessageAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class EditMessageAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        EditMessageAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class DeleteMessageAsyncTask extends AsyncTask<Integer, Void, Void> {

        private NoteDao noteDao;

        DeleteMessageAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            noteDao.delete(integers[0]);
            return null;
        }
    }
}
