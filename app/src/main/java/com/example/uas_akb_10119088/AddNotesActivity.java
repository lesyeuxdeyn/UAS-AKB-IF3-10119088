package com.example.uas_akb_10119088;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

//10119088, IF-3, Laurentius Bryan Hermanto
public class AddNotesActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteCategories, notes;
    TextView date;
    Calendar c;
    String month;
    String todaysDate;
    String curretTime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        toolbar = findViewById(R.id.toolbarAddNotes);
        date = findViewById(R.id.noteDate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        noteTitle = findViewById(R.id.noteTitle);
        noteCategories = findViewById(R.id.noteCategories);
        notes = findViewById(R.id.note);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 ){
                    getSupportActionBar().setTitle(charSequence);
                }else {
                    getSupportActionBar().setTitle("Catatan Baru");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //waktu hari ini
        c = Calendar.getInstance();
        Integer mnth = (c.get(Calendar.MONTH)+1);
        if (mnth == 1){
            month = "Jan";
        }else if (mnth ==2){
            month = "Feb";
        }else if (mnth ==3){
            month = "Mar";
        }else if (mnth ==4){
            month = "Apr";
        }else if (mnth ==5){
            month = "Mei";
        }else if (mnth ==6){
            month = "Jun";
        }else if (mnth ==7){
            month = "Jul";
        }else if (mnth ==8){
            month = "Agu";
        }else if (mnth ==9){
            month = "Sep";
        }else if (mnth ==10){
            month = "Okt";
        }else if (mnth ==11){
            month = "Nov";
        }else if (mnth ==12){
            month = "Des";
        }
        todaysDate =  c.get(Calendar.DAY_OF_MONTH)+ " "  + month + " " + c.get(Calendar.YEAR);
        curretTime = pad(c.get(Calendar.HOUR)) + ":" +pad(c.get(Calendar.MINUTE));
        date.setText(todaysDate);
        Log.d("Kalender","Tanggal dan waktu : "+ todaysDate + " dan "+ curretTime);

    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save){
            Note note = new Note(noteTitle.getText().toString(),noteCategories.getText().toString(), notes.getText().toString(), todaysDate,curretTime);
            com.example.uas_akb_10119088.NoteDB db = new com.example.uas_akb_10119088.NoteDB(this);
            db.addNote(note);
            goToMain();
            Toast.makeText(this, "Catatan Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.close){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain(){
        Intent i = new Intent(this, com.example.uas_akb_10119088.MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMain();
    }
}