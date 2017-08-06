package com.example.thsk.hareskovskole;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.login.CreateUserFragment;
import com.example.thsk.hareskovskole.webservice.ApiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import swagger.model.NewsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends FragmentActivity {

    private int fragmentResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentResource = R.id.main_content;
        ButterKnife.bind(this);
        applyFirstFragment();
        setupRealm();
    }

    private void setupRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }


    private void applyFirstFragment() {
        //not adding this transaction to backStack
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new LoginFragment());
        ft.addToBackStack(LoginFragment.class.getSimpleName());
        ft.commit();
    }
}