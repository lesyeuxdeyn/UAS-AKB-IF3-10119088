package com.example.uas_akb_10119088;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.uas_akb_10119088.databinding.ActivityNotesDetailBinding;
//10119088, IF-3, Laurentius Bryan Hermanto

public class NotesDetail extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNotesDetailBinding binding;
    TextView noteTitle, noteCategories, notes, noteDate;
    com.example.uas_akb_10119088.NoteDB db;
    Note note;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarDetailNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        noteTitle = findViewById(R.id.noteTitleDetail);
        noteCategories = findViewById(R.id.noteCategoriesDetail);
        notes = findViewById(R.id.noteDetail);
        noteDate = findViewById(R.id.noteDateDetail);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID",0);

        db = new com.example.uas_akb_10119088.NoteDB(this);
        Note note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        noteTitle.setText(note.getTitle());
        noteCategories.setText(note.getCategories());
        notes.setText(note.getNotes());
        noteDate.setText("Dibuat : "+note.getDate()+" "+note.getTime());
        notes.setMovementMethod(new ScrollingMovementMethod());

        binding.deleteNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getID());
                Intent i = new Intent(view.getContext(), com.example.uas_akb_10119088.MainActivity.class);
                startActivity(i);
                Toast.makeText(NotesDetail.this, "Catatan Dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.editMenuBtn){
            //mengirim user ke edit activity
            Intent i = new Intent(this, com.example.uas_akb_10119088.EditNotesActivity.class);

            i.putExtra("ID",id);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, com.example.uas_akb_10119088.MainActivity.class);
        startActivity(i);
    }
}