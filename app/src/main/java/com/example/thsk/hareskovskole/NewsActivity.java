package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.thsk.hareskovskole.commercials.CommercialDialog;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.messages.MessageActivity;
import com.example.thsk.hareskovskole.moneytransfer.MoneyTransferActivity;
import com.example.thsk.hareskovskole.news.NewsFragments;
import com.example.thsk.hareskovskole.utils.Data;
import com.example.thsk.hareskovskole.utils.Utility;
import com.urbanairship.Autopilot;
import com.urbanairship.UAirship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends MenuActivity {

    @BindView(R.id.nav_top_left_main_text)
    TextView topLeftMainText;
    @BindView(R.id.nav_top_left_secondary_text)
    TextView topLeftSecondaryText;

    private int fragmentResource = R.id.home_main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
//        applyFirstFragment();

        //setup urbanairship
        initUrbanAirship();

        //setup commercials
        setupCommercials();

        //show commercial
        showCommercial();
    }

    private void setupCommercials() {
        List<CommercialItem> commercials = new ArrayList<>();
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        Data.commercials = commercials;
    }

    private void showCommercial() {
        // get the right commercial
        int numberOfElements = Data.commercials.size();
        CommercialItem commercialToBeShown = Data.commercials.get(Utility.randomNumber(numberOfElements-1,0));

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

}
