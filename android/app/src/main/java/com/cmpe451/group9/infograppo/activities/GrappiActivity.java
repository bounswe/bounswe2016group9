package com.cmpe451.group9.infograppo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.common.adapters.ExpandableListAdapter;
import com.cmpe451.group9.infograppo.network.models.Relation;
import com.cmpe451.group9.infograppo.network.models.Topic;
import com.cmpe451.group9.infograppo.network.models.User;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GrappiActivity extends AppCompatActivity {
    ArrayList<String> topics;
    ArrayAdapter<String> adapter;
    GrappiActivity grappiActivity=this;
    String ourURL="http://52.67.44.90:8080/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grappi);
        final ListView lw = (ListView) findViewById(R.id.list_grappi);
        final String url = ourURL+"topics";//all topics

        MySingleton.getInstance(this).addToRequestQueue(new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        topics = new ArrayList<>();
                        adapter = new ArrayAdapter<>(grappiActivity, android.R.layout.simple_list_item_1,topics);
                        lw.setAdapter(adapter);
                        Topic tmp;
                        JSONObject obj = new JSONObject();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                obj = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tmp = new Gson().fromJson(String.valueOf(obj), Topic.class);
                            try {
                                topics.add(tmp.getName());
                            }catch (Exception e){}
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                }));
        lw.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(grappiActivity,TopicsMapActivity.class));
            }
        });
    }
}
