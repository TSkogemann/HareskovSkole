package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thsk.hareskovskole.commercials.CommercialDialog;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.messages.MessageActivity;
import com.example.thsk.hareskovskole.moneytransfer.MoneyTransferActivity;
import com.example.thsk.hareskovskole.news.NewsFragments;
import com.example.thsk.hareskovskole.utils.Utility;
import com.urbanairship.Autopilot;
import com.urbanairship.UAirship;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_top_left_main_text)
    TextView topLeftMainText;
    @BindView(R.id.nav_top_left_secondary_text)
    TextView topLeftSecondaryText;

    private int fragmentResource = R.id.home_main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFloatingBar();
        applyFirstFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setup urbanairship
        initUrbanAirship();

        //show commercial
        showCommercial();

    }

    private void showCommercial() {
        // get the right commercial
        CommercialItem commercialToBeShown = Utility.getCommercial();

        // Create an instance of the dialog fragment and show it
        Dialog dialog = new CommercialDialog(this, commercialToBeShown);
        dialog.show();
}

    private void initUrbanAirship() {

        Autopilot.automaticTakeOff(this);

        //setting user notifications = true
        UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
        UAirship.shared().getInAppMessageManager().setAutoDisplayEnabled(true);

        //Enabling in-app messaging
        UAirship.shared().getInAppMessageManager().setAutoDisplayEnabled(true);


        //subscribing to tags
        Set<String> tags = new HashSet<>();
        tags.add("test111");
        UAirship.shared().getPushManager().setTags(tags);

        //setting sound
        UAirship.shared().getPushManager().setSoundEnabled(true);

        //printing out channelID
        String channelId = UAirship.shared().getPushManager().getChannelId();
        System.out.println("Urban Airship Application channel ID: " + channelId);

    }

    private void applyFirstFragment() {
        //not adding this transaction to backStack
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new NewsFragments());
        ft.addToBackStack(LoginFragment.class.getSimpleName());
        ft.commit();
    }

    private void initTopbar() {
        topLeftMainText.setText("_Dit navn her");
        topLeftSecondaryText.setText("_blabblal blala");
    }

    private void initFloatingBar() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        getMenuInflater().inflate(R.menu.home, menu);
        // binding after the menu is inflated, so we can bind the menu items aswell
        ButterKnife.bind(this);
        initTopbar();
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

        if (id == R.id.nav_Messages) {

            Intent intent = new Intent(this, MessageActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_hareskovDollars) {

            Intent intent = new Intent(this, MoneyTransferActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Temp) {
            showCommercial();

        } else if (id == R.id.nav_Settings) {

            Intent intent = new Intent(this, MoneyTransferActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
