package com.example.thsk.hareskovskole.overview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import butterknife.ButterKnife;

/**
 * Created by thsk on 07/08/2017.
 */

public class OverviewActivity extends MenuActivity {

    User currentUser;
    private int fragmentResource = R.id.overview_main_content;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_overview);
            ButterKnife.bind(this);
            currentUser = User.getUser();
            applyFirstFragment();
        }

    private void applyFirstFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new OverviewFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
