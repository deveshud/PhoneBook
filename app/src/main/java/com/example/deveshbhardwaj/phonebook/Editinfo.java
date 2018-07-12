package com.example.deveshbhardwaj.phonebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Editinfo extends AppCompatActivity {
    EditText editab1,editab2,editab3;
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);

        Intent in=getIntent();
        final String str=in.getStringExtra("name");
        SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
        String q = "select * from contacts1 where name='"+str+"'";
        Cursor c = db.rawQuery(q, null);
        if(c.moveToFirst())
        {
            do
            {
                {
                    editab1=(EditText)findViewById(R.id.ett1);
                    editab2=(EditText)findViewById(R.id.ett2);
                    editab3=(EditText)findViewById(R.id.ett3);
                    editab1.setText(c.getString(0));
                    editab2.setText(c.getString(1));
                    editab3.setText(c.getString(2));
                    editab1.setTextSize(20);
                    editab2.setTextSize(20);
                    editab3.setTextSize(20);
                }

            }
            while(c.moveToNext());
        }
        db.close();



        bt1=(Button)findViewById(R.id.buttoned);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
                String q="update contacts1 set name='"+editab1.getText().toString()+"', pno='"+editab2.getText().toString()+"',city='"+editab3.getText().toString()+"' where name='"+str+"'";
                db.execSQL(q);
                db.close();
                Toast.makeText(Editinfo.this, "Contacts saved ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
