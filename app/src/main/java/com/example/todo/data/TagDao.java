package com.example.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.models.NoteTag;
import com.example.todo.models.Tag;

import java.util.List;

@Dao
public interface TagDao {
    @Query("DELETE FROM tag")
    void deleteAll();

    @Query("SELECT * FROM tag")
    List<Tag> getAll();
    @Query("SELECT * FROM tag")
    List<Tag> getAllById();

    @Query("SELECT * FROM Tag WHERE id = :id")
    Tag getById(int id);


    @Query("SELECT count() FROM tag WHERE text = :name")
    int countByName(String name);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tag tag);

    @Update
    void update(Tag tag);

    @Delete
    void delete(Tag tag);
}
