package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private DrawerLayout drawerLayout;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_menu, null);
        FrameLayout contentFrameLayout = (FrameLayout) drawerLayout.findViewById(R.id.activity_menu__content_frame_layout);
        contentFrameLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResID, contentFrameLayout);
        super.setContentView(drawerLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
                initTopbar();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentUser = User.getUser();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initTopbar() {
        // setting toolbar color
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColor()));

        ((TextView) findViewById(R.id.nav_top_left_main_text)).setText(currentUser.getName());
        ((TextView) findViewById(R.id.nav_top_left_main_text)).setTextColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColorDark()));
        ((TextView) findViewById(R.id.nav_top_left_secondary_text)).setText(currentUser.getPrimaryEnvironment().getEnvironmentName());
        ((TextView) findViewById(R.id.nav_top_left_secondary_text)).setTextColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColorDark()));
        Glide.with(this)
                .load(currentUser.getPrimaryEnvironment().getSmallLogo())
                .error(R.drawable.ic_menu_camera)
                .fitCenter()
                .into(((ImageView) findViewById(R.id.nav_top_left_icon)));

        // setting custom gradiant as topbar background - primarycolor -> white.

        int bottomWidth = (findViewById(R.id.nav_view)).getWidth();
        ShapeDrawable mDrawableBottom = new ShapeDrawable(new RectShape());
        mDrawableBottom.getPaint().setShader(new LinearGradient(0, 0, bottomWidth, 0,
                Color.parseColor("#ffffff"),
                Color.parseColor(currentUser.getPrimaryEnvironment().getPrimaryColor()),
                Shader.TileMode.CLAMP));
        (findViewById(R.id.nav_view)).setBackground(mDrawableBottom);
        (findViewById(R.id.nav_top_left_background)).setBackgroundDrawable(mDrawableBottom);

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void showCommercial() {
        // get the right commercial
        int numberOfElements = currentUser.getMergedCommercials().size();
        CommercialItem commercialToBeShown = currentUser.getMergedCommercials().get(Utility.randomNumber(numberOfElements - 1, 0));

        // Create an instance of the dialog fragment and show it
        Dialog dialog = new CommercialDialog(this, commercialToBeShown);
        dialog.show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
