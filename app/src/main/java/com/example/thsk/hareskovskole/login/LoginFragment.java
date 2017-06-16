package com.example.thsk.hareskovskole.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thsk.hareskovskole.HomeActivity;
import com.example.thsk.hareskovskole.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 15/06/2017.
 */

public class LoginFragment extends Fragment {
    @BindView(R.id.loginButton)
    Button loginButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;

    }

    private void init() {
    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("sdlkfjsf");
            // going straight to homefragmentactivity like the api call succeded
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    });
    }
}
