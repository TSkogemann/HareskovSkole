package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.thsk.hareskovskole.commercials.CommercialDialog;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.messages.MessageActivity;
import com.example.thsk.hareskovskole.moneytransfer.MoneyTransferActivity;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.realm.RealmUser;

import io.realm.Realm;
import io.realm.RealmResults;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User currentUser;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_menu, null);
        FrameLayout contentFrameLayout = (FrameLayout) drawerLayout.findViewById(R.id.activity_menu__content_frame_layout);
        contentFrameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResID, contentFrameLayout);
        super.setContentView(drawerLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentUser = (User) getIntent().getSerializableExtra("user");
        getCurrentUserFromRealm();
         // setting toolbar color
        toolbar.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColor()));
    }

    private void getCurrentUserFromRealm() {
        Realm myRealm = Realm.getDefaultInstance();
        RealmResults<RealmUser> realmUser = myRealm.where(RealmUser.class).findAll();
        User testUser = Utility.loadCurrentUser();
        System.out.println(realmUser.toString() + " " + testUser.getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initTopbar() {
        ((TextView) findViewById(R.id.nav_top_left_main_text)).setText("_user name");
        ((TextView) findViewById(R.id.nav_top_left_secondary_text)).setText("user enviroment");
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

    private void showCommercial() {
        // get the right commercial
        int numberOfElements = currentUser.getMergedCommercials().size();
        CommercialItem commercialToBeShown = currentUser.getMergedCommercials().get(Utility.randomNumber(numberOfElements - 1, 0));

        // Create an instance of the dialog fragment and show it
        Dialog dialog = new CommercialDialog(this, commercialToBeShown);
        dialog.show();
    }
}
