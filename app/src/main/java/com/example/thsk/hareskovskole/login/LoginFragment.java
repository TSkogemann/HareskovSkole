package com.example.thsk.hareskovskole.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thsk.hareskovskole.NewsActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.Message;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.data.realm.RealmEnvironment;
import com.example.thsk.hareskovskole.utils.data.realm.RealmString;
import com.example.thsk.hareskovskole.utils.data.realm.RealmUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by thsk on 15/06/2017.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.loginButton)
    Button loginButton;
    public User currentUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        init();
        setupUser();
        return view;
    }

    private void init() {
    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // going straight to homefragmentactivity like the api call succeded
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            Utility.saveCurrentUser(currentUser);
            startActivity(intent);
        }
    });
    }


    private void setupUser() {
        String primaryColor = "#e60000";
        String primaryColorDark ="#b30000";
        String accentColor ="#66ff66";
        // secondary env use same random groups
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(true,true,"AlwaysTrue"));
        groups.add(new Group(false,false,"AlwaysFalse"));
        groups.add(new Group(true,false,"TruePaymentFalseMsg"));
        groups.add(new Group(false,true,"FalsePaymentTrueMsg"));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord()));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord()));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord()));
        List<NewsItem> newslist = Utility.getRandomNewsList(15);
        List<Message> messages = Utility.getRandomMessages(5);
        Environment env = new Environment("Hareskov skole",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist);
        User user = new User("Thomas Skogemann", User.UserType.STUDENT,"loginToken1212",messages,env);
        Environment env2 = new Environment("Hareskov skole2",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist);
        Environment env3 = new Environment("Hareskov skole3",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist);
        List<Environment> secondaryEnv = new ArrayList<>();
        secondaryEnv.add(env2);
        secondaryEnv.add(env3);
        user.setSecondaryEnvironments(secondaryEnv);
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
}
