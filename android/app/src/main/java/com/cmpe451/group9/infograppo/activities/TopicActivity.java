package com.cmpe451.group9.infograppo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cmpe451.group9.infograppo.R;

public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Intent intent = getIntent();
        int topicId = intent.getIntExtra("topicId", 0);
        String topicName = intent.getStringExtra("topicName");

        this.setTitle(topicName);
    }
}
