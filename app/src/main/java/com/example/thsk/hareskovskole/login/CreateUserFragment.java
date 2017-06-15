package com.example.thsk.hareskovskole.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.thsk.hareskovskole.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 15/06/2017.
 */

public class CreateUserFragment extends Fragment {

    @BindView(R.id.school)
    Spinner schoolSpinner;
    @BindView(R.id.schoolClass)
    Spinner schoolClassSpinner;
    @BindView(R.id.userType)
    Spinner userTypeSpinner;
    @BindView(R.id.createUserButton)
    Button createUserButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);
        ButterKnife.bind(this,view);
        init();

        return view;
    }

    private void init() {
        initSchoolSpinner();
        initSchoolClassSpinner();
        initUserTypeSpinner();
        initCreateUserButton();
    }

    private void initCreateUserButton() {
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ssdfsdfsdfsfs");
            }
        });
    }

    private void initUserTypeSpinner() {
        ArrayList<String> userTypeList = new ArrayList<>();
        userTypeList.add("elev");
        userTypeList.add("forældre");
        userTypeList.add("lærer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userTypeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userTypeSpinner.setAdapter(dataAdapter);
    }

    private void initSchoolClassSpinner() {
        ArrayList<String> schoolClassList = new ArrayList<>();
        schoolClassList.add("1a");
        schoolClassList.add("1b");
        schoolClassList.add("1c");
        schoolClassList.add("2a");
        schoolClassList.add("2b");
        schoolClassList.add("2c");
        schoolClassList.add("3a");
        schoolClassList.add("3b");
        schoolClassList.add("3c");
        schoolClassList.add("4a");
        schoolClassList.add("4b");
        schoolClassList.add("4c");
        schoolClassList.add("5a");
        schoolClassList.add("5b");
        schoolClassList.add("5c");
        schoolClassList.add("6a");
        schoolClassList.add("6b");
        schoolClassList.add("6c");
        schoolClassList.add("7a");
        schoolClassList.add("7b");
        schoolClassList.add("7c");
        schoolClassList.add("8a");
        schoolClassList.add("8b");
        schoolClassList.add("8c");
        schoolClassList.add("9a");
        schoolClassList.add("9b");
        schoolClassList.add("9c");




        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, schoolClassList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        schoolClassSpinner.setAdapter(dataAdapter);
    }

    private void initSchoolSpinner() {
        ArrayList<String> schoolList = new ArrayList<>();
        schoolList.add("skole1");
        schoolList.add("skole2");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, schoolList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        schoolSpinner.setAdapter(dataAdapter);
    }
}
