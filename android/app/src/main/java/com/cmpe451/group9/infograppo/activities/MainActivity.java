package com.cmpe451.group9.infograppo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cmpe451.group9.infograppo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }

    public void showListView(View view) {
        Intent intent = new Intent(this,ListViewActivity.class);
        startActivity(intent);
    }
}
