package com.example.thsk.hareskovskole.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thsk.hareskovskole.MainActivity;
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
import com.example.thsk.hareskovskole.webservice.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import swagger.model.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thsk on 15/06/2017.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginEmailEt)
    EditText loginEmailEditText;
    @BindView(R.id.loginPasswordEt)
    EditText loginPasswordEditText;
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
                Credentials credentials = new Credentials();
                credentials.setUsername(loginEmailEditText.getText().toString());
                credentials.setPassword(loginPasswordEditText.getText().toString());
                ApiClient.getUserApi().loginUsingPOST(credentials).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            String loginToken = response.headers().get("Authentication");
                            Log.d("LoginFragment", loginToken);
                            currentUser.setLoginToken(loginToken);
                            Utility.saveCurrentUser(currentUser);
                            Intent intent = new Intent(getActivity(), NewsActivity.class);
                            startActivity(intent);
                        } else if (response.code() == 401) {
                            Toast.makeText(LoginFragment.this.getContext(), "incorrect username or password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginFragment.this.getContext(), "Server fejl! - status code=" + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("LoginFragment", "onFailure " + t.getMessage());
                        Toast.makeText(LoginFragment.this.getContext(), "Netværks fejl!", Toast.LENGTH_LONG).show();
                    }
                });
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
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist,Utility.getRandomTransactionList(5));
        User user = new User("Thomas Skogemann", User.UserType.STUDENT,null,messages,env);
        Environment env2 = new Environment("Hareskov skole2",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist,Utility.getRandomTransactionList(5));
        Environment env3 = new Environment("Hareskov skole3",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,primaryColorDark,accentColor,newslist,Utility.getRandomTransactionList(5));
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
