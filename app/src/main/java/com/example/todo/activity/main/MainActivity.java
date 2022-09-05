package com.example.todo.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.activity.Tags;
import com.example.todo.activity.editOrAdd.EditOrAdd;
import com.example.todo.models.Note;
import com.example.todo.models.NoteTag;
import com.example.todo.models.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  String EXTRA_TAG = "NoteDetailActivity.EXTRA_TAG";
    private RecyclerView recyclerView;
    public String _switch = "note";
    private LinearLayout list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);




       /* recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        */

        loadNotes();
    }


    public void loadNotesBtn(View view){
        _switch = "note";
        loadNotes();
    }
    public void loadTagsBtn(View view){
        _switch = "tag";
        loadTags();
    }


    public void loadTags(){

        List<Tag> tags = App.getInstance().getTagDao().getAll();
        list.removeAllViews();
        for (int i = 0; i<tags.size();i++){
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,0,10,20);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(params);
            linearLayout.setBackgroundColor(-16711681);
            linearLayout.setPadding(20,10,20,10);

            TextView name = new TextView(this);
            name.setText(tags.get(i).text);
            name.setTypeface(null, Typeface.BOLD);



            LinearLayout linearLayoutTag = new LinearLayout(this);

            linearLayout.addView(name);
            list.addView(linearLayout);
            int d = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Tags.class);
                    intent.putExtra(EXTRA_TAG, tags.get(d));
                    System.out.println("sssss" + tags.get(d).text);
                    startActivity(intent);
                }
            });
        }

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
            LinearLayout linearLayoutTag = new LinearLayout(this);
            List<NoteTag> noteTags = App.getInstance().getNoteTagDao().getAllNoteTagsByIdNote(notes.get(i).id);
            List<Tag> tags = new ArrayList<>();
            if (noteTags.size()!=0) {
                for (int g = 0; g < noteTags.size(); g++) {
                    tags.add(App.getInstance().getTagDao().getById(noteTags.get(g).idTag));
                }


                if (tags.size() != 0) {
                    for (int g = 0; g < tags.size(); g++) {
                        TextView tag = new TextView(this);
                        System.out.println(tags.get(g));
                        System.out.println(tags.size());
                        System.out.println(g);
                        tag.setText(tags.get(g).text);
                        linearLayoutTag.setBackgroundColor(-16711681);
                        linearLayoutTag.setOrientation(LinearLayout.VERTICAL);
                        linearLayoutTag.setLayoutParams(new LinearLayout.LayoutParams
                                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        linearLayoutTag.addView(tag);
                    }
                }
            }
            linearLayout.addView(date);
            linearLayout.addView(head);
            linearLayout.addView(text);

            linearLayout.addView(linearLayoutTag);
            list.addView(linearLayout);
            int d = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), EditOrAdd.class);
                    intent.putExtra("EXTRA_NOTE", notes.get(d));
                    String text = notes.get(d).text;
                    String head = notes.get(d).head;
                    String date = notes.get(d).date;
                    String id = String.valueOf(notes.get(d).id);
                    System.out.println("ssaaag" + id);
                    intent.putExtra("EXTRA_NOTE_TEXT", text);
                    intent.putExtra("EXTRA_NOTE_HEAD", head);
                    intent.putExtra("EXTRA_NOTE_DATE", date);
                    intent.putExtra("EXTRA_NOTE_ID", id);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (_switch=="tag"){
            loadTags();
        }else{
            loadNotes();
        }


    }
    public void createNew(View view){
        if (_switch=="note") {
            Intent intent = new Intent(this, EditOrAdd.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(this, Tags.class);
            startActivity(intent);
            System.out.println("gg");
        }

    }
}