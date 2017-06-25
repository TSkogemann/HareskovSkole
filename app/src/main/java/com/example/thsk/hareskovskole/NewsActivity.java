package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.example.thsk.hareskovskole.commercials.CommercialDialog;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.news.NewsFragments;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.Utility;
import com.urbanairship.Autopilot;
import com.urbanairship.UAirship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewsActivity extends MenuActivity {

    private int fragmentResource = R.id.home_main_content;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        setupUser();
        setupData();
        applyFirstFragment();

        //setup urbanairship
        initUrbanAirship();

        //show commercial
        showCommercial();
    }

    private void setupData() {
    setTopbarText(currentUser.getName(),currentUser.getPrimaryEnvironment().getEnvironmentName());
    }

    private void setupUser() {
        String primaryColor = "#e60000";
        String primaryColorDark ="#b30000";
        String accentColor ="#66ff66";
        List<String> klasser = new ArrayList<>();
        klasser.add("6A");
        Environment env = new Environment("Hareskov skole",klasser, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(),Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor);
        User user = new User("Thomas Skogemann", User.UserType.STUDENT,env);
        currentUser = user;

    }

    private List<CommercialItem> setupCommercials() {
        List<CommercialItem> commercials = new ArrayList<>();
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        commercials.add(Utility.getCommercial());
        return commercials;
    }

    private void showCommercial() {
        // get the right commercial
        int numberOfElements = currentUser.getMergedCommercials().size();
        CommercialItem commercialToBeShown = currentUser.getMergedCommercials().get(Utility.randomNumber(numberOfElements-1,0));

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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new NewsFragments());
        ft.addToBackStack(LoginFragment.class.getSimpleName());
        ft.commit();
    }

}
