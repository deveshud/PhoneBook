package com.example.deveshbhardwaj.phonebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContact extends AppCompatActivity {
    LinearLayout ll;
    EditText t;
    TextView temp;

    void fill() // this  function displays the name ie all the names from database itself without any filtering
    {
        ll.removeAllViews();// removes anything that is already on the screen
        SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
        String q = "select name from contacts1 order by name";// only 1 column is selected of each row
        Cursor c = db.rawQuery(q, null); //here cursor allows the data in ram of database to be used in java its a kinda a pointer type thing
        if (c.moveToFirst())             // moves to the first row of the database entire row with all columns
        {

            do {
                TextView tv = new TextView(SearchContact.this);
                tv.setText(c.getString(0));// selects only the first column of the table thta is it fetches only the name block of every row only
                tv.setTextSize(30);
                registerForContextMenu(tv);
                ll.addView(tv);
            } while (c.moveToNext());// moves to the next row of the current database

        }
        db.close();
    }

    void fill(String str) // this fill(**) function is used to filter the values
    {
        ll.removeAllViews();
        SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
        String q = "select name from contacts1 where name like '" + str + "%' order by name";// only 1 column is selected of each row only after filtering
        Cursor c = db.rawQuery(q, null); //here cursor allows the data in ram of database to be used in java its a kinda a pointer type thing that points to the data filtered in database ram again and again
        if (c.moveToFirst())             // moves to the first row of the database entire row with all columns
        {

            do {
                TextView tv = new TextView(SearchContact.this);
                tv.setText(c.getString(0));// selects only the first column of the table thta is it fetches only the name block of every row only
                tv.setTextSize(30);
                registerForContextMenu(tv); // this way the contacts filtered are eligible for context menu
                ll.addView(tv);
            } while (c.moveToNext());// moves to the next row of the current database

        }
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        ll = (LinearLayout) findViewById(R.id.search_ll1);
        fill();// calling of fill function
        t = (EditText) findViewById(R.id.search_et1);
        t.addTextChangedListener(new TextWatcher() { // it allows the program to recognise when  the change in text is done and perform the given actions as given below
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fill(t.getText().toString()); // as when the text is modified the changed character is provided to the fill function

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        temp = (TextView) v; // getting the contact on which the screen has been pressed for a long time
        getMenuInflater().inflate(R.menu.context_menu, menu);// Creating Context menu
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_cmenu_edit) {
            Toast.makeText(this, "Editing " + temp.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent in = new Intent(SearchContact.this, Editinfo.class);
            in.putExtra("name", temp.getText().toString());
            startActivity(in);

        } else if (item.getItemId() == R.id.search_cmenu_delete) {

            Toast.makeText(this, "Delete " + temp.getText().toString(), Toast.LENGTH_SHORT).show();
            SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
            String q = "delete from contacts1 where name = '" + temp.getText().toString() + "'";
            db.execSQL(q);
            db.close();
            fill(t.getText().toString());
        } else if (item.getItemId() == R.id.search_cmenu_call) {

            Intent in=new Intent(SearchContact.this,call.class);
            in.putExtra("name",temp.getText().toString());
            startActivity(in);

            Toast.makeText(this, "Calling " + temp.getText().toString(), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.search_cmenu_msg) {
            Intent in=new Intent(SearchContact.this,msg.class);
            in.putExtra("name",temp.getText().toString());
            startActivity(in);


            Toast.makeText(this, "Messaging " + temp.getText().toString(), Toast.LENGTH_SHORT).show();

        }
            return super.onContextItemSelected(item);



    }
}