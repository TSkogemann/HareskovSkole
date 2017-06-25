package com.example.thsk.hareskovskole;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.login.CreateUserFragment;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends FragmentActivity {

    @BindView(R.id.topBarLogin)
    TextView topBarLoginTv;
    @BindView(R.id.topBarNewUser)
    TextView topBarNewUserTv;

    private int fragmentResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentResource = R.id.main_content;
        ButterKnife.bind(this);
        applyFirstFragment();
        setupRealm();
        init();
    }

    private void setupRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void init() {

        topBarLoginTv.setClickable(false);
        topBarLoginTv.setBackgroundResource(R.drawable.side_nav_bar);
        topBarNewUserTv.setClickable(true);
        topBarNewUserTv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        topBarLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarLoginTv.setClickable(false);
                topBarLoginTv.setBackgroundResource(R.drawable.side_nav_bar);
                topBarNewUserTv.setClickable(true);
                topBarNewUserTv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(fragmentResource,new LoginFragment());
                ft.commit();
            }
        });

        topBarNewUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarLoginTv.setClickable(true);
                topBarLoginTv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                topBarNewUserTv.setClickable(false);
                topBarNewUserTv.setBackgroundResource(R.drawable.side_nav_bar);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(fragmentResource, new CreateUserFragment());
                ft.commit();
            }
        });
    }

    private void applyFirstFragment() {
        //not adding this transaction to backStack
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new LoginFragment());
        ft.addToBackStack(LoginFragment.class.getSimpleName());
        ft.commit();
    }
}