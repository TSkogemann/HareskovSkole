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
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Environment;
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
    private Realm myRealm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        init();
        setupUser();
        setupRealm();
        return view;
    }

    private void setupRealm() {
        myRealm = Realm.getDefaultInstance();
    }



    private void init() {
    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // going straight to homefragmentactivity like the api call succeded
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            intent.putExtra("user",currentUser);
            saveCurrentUser(currentUser);
            startActivity(intent);
        }
    });
    }

    private void saveCurrentUser(User currentUser) {
        Environment primaryEnviroment = currentUser.getPrimaryEnvironment();
        myRealm.beginTransaction();

        // realm user
        RealmUser realmUser = myRealm.createObject(RealmUser.class);
        realmUser.setName(currentUser.getName());
        realmUser.setUsertype(currentUser.getUserType().toString());

        // primary environment
        RealmEnvironment realmPrimaryEnvironment = myRealm.createObject(RealmEnvironment.class);

        //environment info
        realmPrimaryEnvironment.setEnvironmentName(primaryEnviroment.getEnvironmentName());
        realmPrimaryEnvironment.setEnvironmentType(primaryEnviroment.getEnvironmentType().toString());

        // account balance
        realmPrimaryEnvironment.setAccountBalance(primaryEnviroment.getAccountBalance());

        //primary environment styles
        realmPrimaryEnvironment.setLogo(primaryEnviroment.getLogo());
        realmPrimaryEnvironment.setSmallLogo(primaryEnviroment.getSmallLogo());
        realmPrimaryEnvironment.setPrimaryColor(primaryEnviroment.getPrimaryColor());
        realmPrimaryEnvironment.setPrimaryColorDark(primaryEnviroment.getPrimaryColorDark());
        realmPrimaryEnvironment.setAccentColor(primaryEnviroment.getAccentColor());

        //setting primary realm groups
        RealmList<RealmString> realmGroups = new RealmList<>();
        for (String group : primaryEnviroment.getGroups()){
            RealmString temp = new RealmString();
            temp.setString(group);
            realmGroups.add(myRealm.copyToRealm(temp));
        }
        realmPrimaryEnvironment.setGroups(realmGroups);

        //setting primary environment to user
        realmUser.setPrimaryEnvironment(realmPrimaryEnvironment);
        myRealm.commitTransaction();
    }

    private void setupUser() {
        String primaryColor = "#e60000";
        String primaryColorDark ="#b30000";
        String accentColor ="#66ff66";
        List<String> klasser = new ArrayList<>();
        klasser.add("6A");
        Environment env = new Environment("Hareskov skole",klasser, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor);
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
}
