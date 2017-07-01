package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.Message;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 20/06/2017.
 */

public class MessageActivity extends MenuActivity {

    @BindView(R.id.message_choose_reciever_spinner)
    Spinner msgChooseRecieverSpinner;

    User currentUSer;
    List<String> listOfRecievers;
    List<Message> currentChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        currentUSer = Utility.loadCurrentUser();
        initListOfRecievers();
        initMsgChooseRecieverSpinner();
    }

    private void initMsgChooseRecieverSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOfRecievers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        msgChooseRecieverSpinner.setAdapter(dataAdapter);

        msgChooseRecieverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        }

    private void initListOfRecievers() {
        listOfRecievers = new ArrayList<>();
        if(currentUSer.getPrimaryEnvironment().getGroups().size()>0){
            for(Group group : currentUSer.getPrimaryEnvironment().getGroups()){
                if(group.getAllowMessages()){
                    listOfRecievers.add(group.getName());
                }
            }
        }
        if (currentUSer.getSecondaryEnvironments().size()>0){
            for (Environment env : currentUSer.getSecondaryEnvironments()){
                if(env.getGroups().size()>0){
                    for (Group group : env.getGroups()){
                        if(group.getAllowMessages()){
                            listOfRecievers.add(group.getName());
                        }
                    }
                }
            }
        }
    }
}
