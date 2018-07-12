package com.example.deveshbhardwaj.phonebook;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class msg extends AppCompatActivity {
    TextView t1;
    EditText t2;
    Button b1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        ActivityCompat.requestPermissions(msg.this,new String[]{Manifest.permission.SEND_SMS},1);
        Intent in = getIntent();
        final String str = in.getStringExtra("name");
        SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
        String q = "select * from contacts1 where name='" + str + "'";
        Cursor c = db.rawQuery(q, null);
        t1=(TextView)findViewById(R.id.t1);
        b1=(Button)findViewById(R.id.b1);
        t2=(EditText)findViewById(R.id.t2);
        if (c.moveToFirst())
            {
            do      {

                    t1.setText(c.getString(1));
                    }while (c.moveToNext());
           }

            db.close();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm=SmsManager.getDefault();
                sm.sendTextMessage(t1.getText().toString(),null,t2.getText().toString(),null,null);
            }
        });

        }
    }

