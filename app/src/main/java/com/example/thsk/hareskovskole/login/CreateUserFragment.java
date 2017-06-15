package com.example.thsk.hareskovskole.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    @BindView(R.id.school_class)
    Spinner schoolClassSpinner;
    @BindView(R.id.userType)
    Spinner userTypeSpinner;

    @BindView(R.id.create_user_button)
    Button createUserButton;

    @BindView(R.id.select_school_LL)
    LinearLayout selectSchoolLL;
    @BindView(R.id.select_SchoolClass_LL)
    LinearLayout selectSchoolClassLL;
    @BindView(R.id.create_user_auth_code_LL)
    LinearLayout createUserAuthCodeLL;
    @BindView(R.id.create_user_email_LL)
    LinearLayout emailLL;
    @BindView(R.id.create_user_confirm_email_LL)
    LinearLayout confirmEmailLL;
    @BindView(R.id.create_user_first_name_LL)
    LinearLayout createUserFirstNameLL;
    @BindView(R.id.create_user_last_name_LL)
    LinearLayout createUserLastNameLL;
    @BindView(R.id.create_user_auth_code_validation_LL)
    LinearLayout createUserAuthCodeValidationLL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);
        ButterKnife.bind(this, view);
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
        // these are tied to the switch in onItemSelected. Should be changed later on to a safer structure
        ArrayList<String> userTypeList = new ArrayList<>();
        userTypeList.add("elev");
        userTypeList.add("forældre");
        userTypeList.add("lærer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userTypeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userTypeSpinner.setAdapter(dataAdapter);

        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0: {
                        System.out.println("elev");
                        //skole, klasse, navn, email, auth kode
                        selectSchoolLL.setVisibility(View.VISIBLE);
                        selectSchoolClassLL.setVisibility(View.VISIBLE);
                        createUserAuthCodeLL.setVisibility(View.VISIBLE);
                        createUserAuthCodeValidationLL.setVisibility(View.VISIBLE);
                        createUserFirstNameLL.setVisibility(View.GONE);
                        createUserLastNameLL.setVisibility(View.GONE);
                        emailLL.setVisibility(View.GONE);
                        confirmEmailLL.setVisibility(View.GONE);
                        break;
                    }
                    case 1: {
                        System.out.println("forældre");
                        // navn, email
                        selectSchoolLL.setVisibility(View.GONE);
                        selectSchoolClassLL.setVisibility(View.GONE);
                        createUserAuthCodeLL.setVisibility(View.GONE);
                        createUserAuthCodeValidationLL.setVisibility(View.GONE);
                        createUserFirstNameLL.setVisibility(View.VISIBLE);
                        createUserLastNameLL.setVisibility(View.VISIBLE);
                        emailLL.setVisibility(View.VISIBLE);
                        confirmEmailLL.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 2: {
                        System.out.println("lærer");
                        //skole, klasse, navn, email, auth kode
                        selectSchoolLL.setVisibility(View.VISIBLE);
                        selectSchoolClassLL.setVisibility(View.VISIBLE);
                        createUserAuthCodeLL.setVisibility(View.VISIBLE);
                        createUserAuthCodeValidationLL.setVisibility(View.VISIBLE);
                        createUserFirstNameLL.setVisibility(View.GONE);
                        createUserLastNameLL.setVisibility(View.GONE);
                        emailLL.setVisibility(View.GONE);
                        confirmEmailLL.setVisibility(View.GONE);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
