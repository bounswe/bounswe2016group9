package com.cmpe451.group9.infograppo.activities;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.network.models.User;
import com.cmpe451.group9.infograppo.network.services.MySingleton;
import com.google.gson.Gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Contributions");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Favorites");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Followings");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Followers");
        host.addTab(spec);

        final TextView userName = (TextView) findViewById(R.id.text_profile_username);
        final TextView userInfo = (TextView) findViewById(R.id.text_profile_userinfo);
        final TextView userFollow = (TextView) findViewById(R.id.text_profile_userfollow);

        String url = "http://52.67.44.90:8080/users/2";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        User tmp = new Gson().fromJson(String.valueOf(response), User.class);
                        String utmp = tmp.getName() + " " + tmp.getSurname()
                                + " ("+tmp.getUsername()+")";
                        userName.setText(utmp);
                        utmp= tmp.getAge() + " , " +tmp.getEmail();
                        userInfo.setText(utmp);
                        int followers = 123, followings = 36;
                        utmp= followers+" followers, "+followings+" followings";
                        userFollow.setText(utmp);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    /**
     * ChangePicture method in Profile section
     * enables a user to change his/her profile picture
     * @param view the image that will be uploaded to the user's profile
     */
    public void changePicture(View view) {
        ImageView iw= (ImageView) findViewById(R.id.image_profile);
        iw.setImageResource(R.drawable.menaf);
    }
}
