package com.example.todo.activity.editOrAdd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.activity.main.MainActivity;
import com.example.todo.models.Note;

import java.util.concurrent.ExecutionException;

public class EditOrAdd extends AppCompatActivity {

    boolean isNew = false;
    private Note note;
    private EditText head;
    private EditText text1;
    private EditText date;
    public static void start(Activity caller, Note note){
        Intent intent = new Intent(caller, EditOrAdd.class);
        if (note!=null){
            intent.putExtra("EXTRA_NOTE", note);
        }
        caller.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_or_add);
        text1 = findViewById(R.id.text);
        head = findViewById(R.id.editHead);
        date = findViewById(R.id.editDate);
        System.out.println(getIntent().hasExtra("EXTRA_NOTE"));
        System.out.println("ff" + getIntent().getParcelableExtra("EXTRA_NOTE"));


        if (getIntent().hasExtra("EXTRA_NOTE_HEAD")){
            head.setText(getIntent().getStringExtra("EXTRA_NOTE_HEAD"));
            text1.setText(getIntent().getStringExtra("EXTRA_NOTE_TEXT"));
            date.setText(getIntent().getStringExtra("EXTRA_NOTE_DATE"));
            isNew = false;

        }else{
            note = new Note();
            note.date = "";
            note.text = "";
            note.head ="";
            isNew = true;

        }
    }
    public void save(View view){
        if (text1.getText().length()>0 && head.getText().length()>0){
            note = new Note();

            note.text = text1.getText().toString();
            note.head = head.getText().toString();
            note.date =date.getText().toString();
            if (isNew) {
                App.getInstance().getNoteDao().insert(note);
            }
            else{
                note.id = Integer.parseInt(getIntent().getStringExtra("EXTRA_NOTE_ID"));
                App.getInstance().getNoteDao().update(note);
            }
            finish();





        }
    }
    public void back(View view){
        finish();
    }
    public void delete(View view){
        if (!isNew) {
            App.getInstance().getNoteDao().delete(note);
        }
        finish();
    }
}