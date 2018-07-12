package com.example.deveshbhardwaj.phonebook;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText t1,t2,t3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        t1=(EditText)findViewById(R.id.add_t1);
        t2=(EditText)findViewById(R.id.add_t2);
        t3=(EditText)findViewById(R.id.add_t3); // changes the output from view type to the respective type
        b1=(Button)findViewById(R.id.add_b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("MyPB",MODE_PRIVATE,null);// Creates a database if allready there updates it
                String q="create table if not exists contacts1 (name varchar,pno varchar,city varchar)"; // creates a table which stores name phone number and city and labelled as q
                db.execSQL(q); // executes the new database
                q="insert into contacts1 values('"+t1.getText().toString()+"','"+t2.getText().toString()+"','"+t3.getText().toString()+"')"; // inserts into the new database
                db.execSQL(q);
                db.close(); //closes the databse
                Toast.makeText(AddContact.this, "Your Contact has been saved", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

}
