package com.example.deveshbhardwaj.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);  // Any function is operating  at instant of starting an activity
        setContentView(R.layout.activity_home);// it tells the layout of the activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi=getMenuInflater(); //
        mi.inflate(R.menu.main_menu,menu);//    R.menu.main_menu tells that the menu which is main menu is to be over ridden with this main_menu ...and menu keyword after that tells that the menu is main menu and not context menu
        return super.onCreateOptionsMenu(menu);// used for inflating and overriding .. the main menu and its contents
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_c)
        {
            Intent in=new Intent(Home.this,AddContact.class); //this  moves from current activity to the next activity ..IT DECIDES TO MOVE
            startActivity(in);// IT INITIATES THE MOVEMENT
        }
        else
        {
            Intent in=new Intent(Home.this,SearchContact.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}
