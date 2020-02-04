package com.example.retrofitexample.CURD.SQLite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retrofitexample.CURD.ModelRetrofit.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Query("delete from ajith where id LIKE :number")
    void delete(int number);

    @Query(" delete from ajith")
    void deleteAll();

    @Query(" select * from ajith order by id desc")
    LiveData<List<Note>> getAllMessages();
}
