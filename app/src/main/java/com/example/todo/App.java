package com.example.todo;

import android.app.Application;

import androidx.room.Room;

import com.example.todo.data.AppDatabase;
import com.example.todo.data.NoteDao;
import com.example.todo.data.NoteTagDao;
import com.example.todo.data.TagDao;
import com.example.todo.models.NoteTag;

public class App extends Application {
    private AppDatabase database;
    private NoteDao noteDao;
    private NoteTagDao noteTagDao;
    private TagDao tagDao;
    private static App instance;
    public static App getInstance(){
        return instance;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"app-db")
                .allowMainThreadQueries()
                .build();
        noteDao = database.noteDao();
        noteTagDao = database.noteTagDao();
        tagDao = database.tagDao();

    }

    public AppDatabase getDatabase() {
        return database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }
    public NoteTagDao getNoteTagDao() {
        return noteTagDao;
    }
    public TagDao getTagDao() {
        return tagDao;
    }

}
