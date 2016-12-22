package com.cmpe451.group9.infograppo.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cmpe451.group9.infograppo.R;
import com.cmpe451.group9.infograppo.common.fragments.GrappiFragment;
import com.cmpe451.group9.infograppo.common.fragments.ProfileFragment;
import com.cmpe451.group9.infograppo.common.fragments.SettingsFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;


public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String baseURL ="http://52.67.44.90:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton grappi = (FloatingActionButton) findViewById(R.id.grappi);
        grappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grappi.setTitle("Grappi Clicked");
            }
        });

        final FloatingActionButton createTopic = (FloatingActionButton) findViewById(R.id.create_topic);
        createTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTopic.setTitle("Create Topic Clicked");
            }
        });

        final FloatingActionButton addRelation = (FloatingActionButton) findViewById(R.id.add_relation);
        addRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRelation.setTitle("Add Relation Clicked");
            }
        });

        final FloatingActionButton search = (FloatingActionButton) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setTitle("Search Clicked");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // update the main content by replacing fragments
        Fragment fragment;
        // For AppCompat use getSupportFragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.home) {
            this.setTitle(R.string.home);
            fragment = new GrappiFragment();

        } else if (id == R.id.profile) {
            this.setTitle(R.string.profile);

            fragment = new ProfileFragment();

        } else if (id == R.id.settings) {
            this.setTitle(R.string.settings);

            fragment = new SettingsFragment();

        } else {
            this.setTitle(R.string.grappi);

            fragment = new GrappiFragment();

        }
        fragmentManager.beginTransaction().replace(R.id.content_navigation_drawer, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changePicture(View view) {
        ImageView iw= (ImageView) findViewById(R.id.image_profile);
        iw.setImageResource(R.drawable.menaf);
    }

    public static String getBaseURL() {
        return baseURL;
    }
}
