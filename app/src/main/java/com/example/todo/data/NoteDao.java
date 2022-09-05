package com.example.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.models.Note;
import com.example.todo.models.NoteTag;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("DELETE FROM note")
    void deleteAll();

    @Query("SELECT * FROM note")
    List<Note> getAll();
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllLiveData();
    @Query("SELECT * FROM note WHERE head = :head")
    Note getByHead(String head);
    @Query("SELECT * FROM note WHERE id = :id")
    Note getById(int id);




    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);



}
