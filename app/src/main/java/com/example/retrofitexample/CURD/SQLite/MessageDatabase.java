package com.example.retrofitexample.CURD.SQLite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

@Database(entities = {Note.class}, version = 3, exportSchema = false)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase messageDatabase;
    public abstract NoteDao messageDao();

    static synchronized MessageDatabase getInstance(Context context){
        if (messageDatabase == null){
            messageDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    MessageDatabase.class, "message_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return messageDatabase;
    }
}
