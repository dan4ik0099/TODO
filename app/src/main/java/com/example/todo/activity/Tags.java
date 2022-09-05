package com.example.todo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.activity.editOrAdd.EditOrAdd;
import com.example.todo.models.Note;
import com.example.todo.models.NoteTag;
import com.example.todo.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class Tags extends AppCompatActivity {
    private static final  String EXTRA_TAG = "NoteDetailActivity.EXTRA_TAG";
    boolean isNew = false;
    private Tag tag;
    private EditText name;
    private LinearLayout list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_tags);

        name = findViewById(R.id.editTagName);
        list = findViewById(R.id.list);

        if (getIntent().hasExtra(EXTRA_TAG)){
            tag= getIntent().getParcelableExtra(EXTRA_TAG);
            name.setText(tag.text);
            isNew = false;
        }else{
            tag = new Tag();
            tag.text="";

            isNew = true;
        }
        loadNotes();
    }


    public void loadNotes(){

        List<Note> notes = App.getInstance().getNoteDao().getAll();
        list.removeAllViews();
        for (int i = 0; i<notes.size();i++){
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,0,10,20);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(params);
            linearLayout.setBackgroundColor(-256);
            linearLayout.setPadding(20,10,20,10);

            TextView date = new TextView(this);
            date.setText(String.valueOf(notes.get(i).date));

            TextView head = new TextView(this);
            head.setTypeface(null, Typeface.BOLD);
            head.setText(notes.get(i).head);
            head.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView text = new TextView(this);
            text.setText(notes.get(i).text);


            List<NoteTag> noteTags = App.getInstance().getNoteTagDao().getAllNoteTagsByIdNote(notes.get(i).id);
            List<Tag> tags = new ArrayList<>();
            for (int g = 0;g<noteTags.size();g++){
                tags.add(App.getInstance().getTagDao().getById(noteTags.get(g).idTag));
            }
            LinearLayout linearLayoutTag = new LinearLayout(this);
            for (int g = 0;g<tags.size();g++){
                TextView tag = new TextView(this);
                tag.setText(tags.get(g).text);
                linearLayoutTag.setBackgroundColor(-16711681);
                linearLayoutTag.setOrientation(LinearLayout.VERTICAL);
                linearLayoutTag.setLayoutParams(new LinearLayout.LayoutParams
                                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayoutTag.addView(tag);
            }
            System.out.println(App.getInstance().getNoteTagDao().getAll().size());
            linearLayout.addView(date);
            linearLayout.addView(head);
            linearLayout.addView(text);
            linearLayout.addView(linearLayoutTag);
            list.addView(linearLayout);
            int d = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteTag noteTagAlready
                            = App.getInstance().getNoteTagDao().getTagByIdNoteAndTag(notes.get(d).id, tag.id);
                    NoteTag noteTag = new NoteTag();
                    noteTag.idNote = notes.get(d).id;
                    noteTag.idTag = tag.id;
                    if(noteTagAlready==null){

                        App.getInstance().getNoteTagDao().insert(noteTag);

                    }else{
                        App.getInstance().getNoteTagDao().delete(noteTagAlready);
                    }
                    loadNotes();

                }
            });
        }
    }




    public void save(View view){
        if (name.getText().length()>0 && App.getInstance().getTagDao().countByName(name.getText().toString())==0){
            tag.text = name.getText().toString();


            if (isNew) {
                App.getInstance().getTagDao().insert(tag);
            }
            else{
                App.getInstance().getTagDao().update(tag);
            }
            finish();
        }
    }
    public void back(View view){
        finish();
    }
    public void delete(View view){
        if (!isNew) {
            App.getInstance().getTagDao().delete(tag);
            App.getInstance()
                    .getNoteTagDao().deleteAllByIdTag(tag.id);


        }
        finish();
    }

}