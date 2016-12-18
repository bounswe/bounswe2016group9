package com.cmpe451.group9.infograppo.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.cmpe451.group9.infograppo.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, NavigationDrawerActivity.class);
//        startActivity(intent);
//    }
//
//    public void showListView(View view) {
//        Intent intent = new Intent(this,TopicsMapActivity.class);
//        startActivity(intent);
//    }
//    public void showProfile(View view) {
//        Intent intent = new Intent(this,ProfileActivity.class);
//        startActivity(intent);
//    }
//    public void showGrappi(View view) {
//        Intent intent = new Intent(this,GrappiActivity.class);
//        startActivity(intent);
//    }
}
