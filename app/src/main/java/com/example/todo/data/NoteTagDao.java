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
import com.example.todo.models.Tag;

import java.util.List;

@Dao
public interface NoteTagDao {
    @Query("DELETE FROM noteTag")
    void deleteAll();
    @Query("DELETE FROM noteTag WHERE idTag = :id")
    void deleteAllByIdTag(int id);


    @Query("SELECT * FROM noteTag")
    List<NoteTag> getAll();
    @Query("SELECT * FROM noteTag")
    LiveData<List<NoteTag>> getAllLiveData();

    @Query("SELECT * FROM noteTag WHERE idNote = :id")
    List<NoteTag> getAllByIdNote(int id);

    @Query("SELECT * FROM notetag WHERE idNote = :id")
    List<NoteTag> getAllNoteTagsByIdNote(int id);


    @Query("SELECT * FROM notetag WHERE idNote = :id AND idTag= :idTag")
    NoteTag getTagByIdNoteAndTag(int id, int idTag);




    @Query("SELECT * FROM noteTag WHERE id = :id")
    NoteTag getById(int id);




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteTag noteTag);

    @Update
    void update(NoteTag noteTag);

    @Delete
    void delete(NoteTag noteTag);
}
