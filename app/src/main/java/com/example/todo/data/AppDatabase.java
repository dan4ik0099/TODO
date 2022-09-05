package com.example.todo.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todo.models.Note;
import com.example.todo.models.NoteTag;
import com.example.todo.models.Tag;

@Database(entities = {Note.class, Tag.class, NoteTag.class}, version = 9, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract NoteTagDao noteTagDao();
    public abstract TagDao tagDao();


}
