package com.example.thsk.hareskovskole.login;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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

    @BindView(R.id.login_background_vv)
    VideoView backgroundVideovv;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginEmailEt)
    EditText loginEmailEditText;
    @BindView(R.id.loginPasswordEt)
    EditText loginPasswordEditText;
    @BindView(R.id.login_forgot_password_tv)
    TextView loginForgotPasswordTv;
    @BindView(R.id.login_new_user_tv)
    TextView loginNewUserTv;
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

    @Override
    public void onResume() {
        super.onResume();
        backgroundVideovv.setAlpha(0);
        //backgroundVideovv.setScaleMode(VideoTextureView.FIT_WIDTH);
        Uri videoUri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.video2);
        backgroundVideovv.setVideoURI(videoUri);
        backgroundVideovv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //removing sound
                mp.setVolume(0f,0f);
                //setting looping
                mp.setLooping(true);
            }
        });
       // backgroundVideovv.setScaleX(3f);
       // backgroundVideovv.setScaleY(3f);
        backgroundVideovv.start();
        backgroundVideovv.start();
        backgroundVideovv.post(new Runnable() {
            @Override
            public void run() {
                if (null != backgroundVideovv) {
                    backgroundVideovv.animate()
                            .alpha(1)
                            .setDuration(3000)
                            .setInterpolator(new LinearOutSlowInInterpolator())
                            .start();
                }
            }
        });
    }

    private void init() {

        loginForgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("forgot password clicked!");
            }
        });

        loginNewUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("new user clicked!");

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, new CreateUserFragment(), "NewFragmentTag");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cheatlogin
                cheatLogin();

                Credentials credentials = new Credentials();
                credentials.setUsername(loginEmailEditText.getText().toString());
                credentials.setPassword(loginPasswordEditText.getText().toString());
                ApiClient.getUserApi().loginUsingPOST(credentials).enqueue(new Callback<swagger.model.User>() {
                    @Override
                    public void onResponse(Call<swagger.model.User> call, Response<swagger.model.User> response) {
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
                    public void onFailure(Call<swagger.model.User> call, Throwable t) {
                        Log.d("LoginFragment", "onFailure " + t.getMessage());
                        Toast.makeText(LoginFragment.this.getContext(), "Netværks fejl!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    private void setupUser() {
        String primaryColor = "#00b300";
        String primaryColorDark ="#008000";
        String accentColor ="#66ff66";
        // secondary env use same random groups
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(true,true,"AlwaysTrue",Utility.getRandomMessages(10),Utility.randomText(10,2),Utility.randomPicture(),Utility.randomText(5,1),Utility.randomText(3,1),Utility.getRandomWord(),true,Utility.randomPicture()));
        groups.add(new Group(false,false,"AlwaysFalse"));
        groups.add(new Group(true,false,"TruePaymentFalseMsg"));
        groups.add(new Group(false,true,"FalsePaymentTrueMsg",Utility.getRandomMessages(3),Utility.randomText(10,2),Utility.randomPicture(),Utility.randomText(5,1),Utility.randomText(3,1),Utility.getRandomWord(),true,Utility.randomPicture()));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord(),Utility.randomText(10,2),Utility.randomPicture(),Utility.randomText(5,1),Utility.randomText(3,1),Utility.getRandomWord(),true,Utility.randomPicture()));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord(),Utility.randomText(10,2),Utility.randomPicture(),Utility.randomText(5,1),Utility.randomText(3,1),Utility.getRandomWord(),true,Utility.randomPicture()));
        groups.add(new Group(Utility.getRandomBoolean(), Utility.getRandomBoolean(),Utility.getRandomWord(),Utility.randomText(10,2),Utility.randomPicture(),Utility.randomText(5,1),Utility.randomText(3,1),Utility.getRandomWord(),true,Utility.randomPicture()));
        List<NewsItem> newslist = Utility.getRandomNewsList(15);
        Environment env = new Environment("Hareskov skole",groups, Environment.EnvironmentType.SCHOOL,
                150,setupCommercials(), Utility.randomPicture(),Utility.randomPicture(),primaryColor,
                primaryColorDark,accentColor,newslist,Utility.getRandomTransactionList(5),Utility.getRandomMessages(10));
        User user = new User("Thomas Skogemann", User.UserType.STUDENT,null,env);
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

    private void cheatLogin(){
        Utility.saveCurrentUser(currentUser);
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        startActivity(intent);
    }
}
