package com.example.deveshbhardwaj.phonebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class call extends AppCompatActivity {
    EditText t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ActivityCompat.requestPermissions(call.this,new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE},1);
        t1=(EditText)findViewById(R.id.t1);

        b1=(Button)findViewById(R.id.b1);
        Intent in = getIntent();
        final String str = in.getStringExtra("name");
        SQLiteDatabase db = openOrCreateDatabase("MyPB", MODE_PRIVATE, null);
        String q = "select * from contacts1 where name='" + str + "'";
        Cursor c = db.rawQuery(q, null);

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
                Intent phoneIntent=new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:"+t1.getText()));
                if(ActivityCompat.checkSelfPermission(call.this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(phoneIntent);
            }
        });
    }
}
