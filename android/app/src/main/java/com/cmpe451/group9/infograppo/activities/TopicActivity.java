package com.cmpe451.group9.infograppo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.network.models.Post;
import com.cmpe451.group9.infograppo.network.models.Topic;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TopicActivity extends AppCompatActivity {

    ArrayList<String> topics;
    ArrayAdapter<String> adapter;
    TopicActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Intent intent = getIntent();
        int topicId = intent.getIntExtra("topicId", 0);
        String topicName = intent.getStringExtra("topicName");

        this.setTitle(topicName);

        String topicURL = NavigationDrawerActivity.baseURL   + "/" + topicId + "/posts";

        final ListView listView = (ListView) findViewById(R.id.list_post);

        MySingleton.getInstance(this).addToRequestQueue(new JsonArrayRequest
                (Request.Method.GET, topicURL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        topics = new ArrayList<>();
                        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, topics);
                        listView.setAdapter(adapter);
                        Post tmp;
                        JSONObject obj = new JSONObject();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                obj = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tmp = new Gson().fromJson(String.valueOf(obj), Post.class);
                            try {
                                topics.add(tmp.getContent());
                            } catch (Exception ignored){}
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }));

    }
}
