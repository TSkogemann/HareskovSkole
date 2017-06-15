package com.example.thsk.hareskovskole;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.login.CreateUserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
        init();
    }

    private void init() {

        topBarLoginTv.setClickable(false);
        topBarNewUserTv.setClickable(true);
        topBarLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarLoginTv.setClickable(false);
                topBarNewUserTv.setClickable(true);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(fragmentResource,new LoginFragment());
                ft.commit();
            }
        });

        topBarNewUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarLoginTv.setClickable(true);
                topBarNewUserTv.setClickable(false);
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


    // onclicks
    @OnClick(R.id.topBarLogin)
    public void onTopBarLoginClick(View view) {


    }

    @OnClick(R.id.topBarNewUser)
    public void onTopBarNewUserClick(View view) {

    }
}