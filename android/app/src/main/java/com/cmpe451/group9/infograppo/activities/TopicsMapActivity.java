package com.cmpe451.group9.infograppo.activities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cmpe451.group9.infograppo.common.adapters.ExpandableListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.network.models.Relation;
import com.cmpe451.group9.infograppo.network.models.Topic;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicsMapActivity extends Activity {

    String ourTopic="7";
    String ourURL="http://52.67.44.90:8080/";

    List<String> relatedTopics;
    Map<String, List<String>> topicsWithRelations;
    ExpandableListView expListView;
    TopicsMapActivity topicsMapActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        expListView = (ExpandableListView) findViewById(R.id.list_expandable);
        relatedTopics = new ArrayList<>();
        topicsWithRelations = new LinkedHashMap<>();
        setGroupIndicatorToRight();
        final String url = ourURL+"topics/"+ourTopic+"/relationsFrom";//for relations From, not To

        MySingleton.getInstance(this).addToRequestQueue(new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Relation tmp;
                        ArrayList<String> allTops= new ArrayList<>();
                        Map<String, List<String>> topicWithRelations= new LinkedHashMap<>();
                        JSONObject obj = new JSONObject();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                obj = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tmp = new Gson().fromJson(String.valueOf(obj), Relation.class);
                            String relatedTopicName="";//related topic name
                            try {
                                relatedTopicName= tmp.getToTopic().getName();//topic name "To" relations
                            }catch (Exception e){}
                            List<String> content= new ArrayList<>();//all relations content related to this topic
                            try {
                                if(topicWithRelations.containsKey(relatedTopicName)) {
                                    content= topicWithRelations.get(relatedTopicName);
                                    content.add(tmp.getContent());
                                }else {
                                    content.add(tmp.getContent());
                                    allTops.add(relatedTopicName);
                                }
                            }catch(Exception e){}
                            topicWithRelations.put(relatedTopicName, content);
                        }
                        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                                topicsMapActivity, allTops, topicWithRelations);

                        expListView.setAdapter(expListAdapter);


                        expListView.setOnChildClickListener(new OnChildClickListener() {

                            public boolean onChildClick(ExpandableListView parent, View v,
                                                        int groupPosition, int childPosition, long id) {
                                final String selected = (String) expListAdapter.getChild(
                                        groupPosition, childPosition);
                                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                                        .show();

                                return true;
                            }
                        });
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }));

    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }
    public void relationUp(View view) {
        TextView rate = (TextView) findViewById(R.id.text_rate_relation);
        String rr= (Integer.parseInt((String)rate.getText())+1)+"";
        rate.setText(rr);
    }
    public void relationDown(View view) {
        TextView rate = (TextView) findViewById(R.id.text_rate_relation);
        String rr= (Integer.parseInt((String)rate.getText())-1)+"";
        rate.setText(rr);
    }
    public void goThatTopic(View view) {//when click on the topic

    }
}
