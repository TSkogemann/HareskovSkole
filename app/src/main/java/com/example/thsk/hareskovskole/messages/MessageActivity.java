package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.login.LoginFragment;
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

    private int fragmentResource;

    User currentUSer;
    List<ChatObject> recieverList;
    private ChatObject sendToName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        fragmentResource = R.id.message_conversation;
        currentUSer = Utility.loadCurrentUser();

        // init spinner
        initListOfRecievers();
        MessageAdapter dataAdapter = new MessageAdapter(recieverList,this);
        msgChooseRecieverSpinner.setAdapter(dataAdapter);

        msgChooseRecieverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendToName = recieverList.get(position);
                System.out.println("name picked " +sendToName);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chat",sendToName);
                MessageConversationFragment msgFragment = new MessageConversationFragment();
                msgFragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(fragmentResource, msgFragment);
                ft.addToBackStack(MessageConversationFragment.class.getSimpleName());
                ft.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    applyFirstFragment();
    }

    private void applyFirstFragment() {
        //not adding this transaction to backStack
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new MessageConversationFragment());
        ft.addToBackStack(MessageConversationFragment.class.getSimpleName());
        ft.commit();
    }


    private void initListOfRecievers() {
        recieverList = new ArrayList<>();
        // adding primary env to list
        String envName =  currentUSer.getPrimaryEnvironment().getEnvironmentName();
        List<Message> messages;
        if ( currentUSer.getPrimaryEnvironment().getMessages()!= null) {
            messages = currentUSer.getPrimaryEnvironment().getMessages();
        } else {
            messages = new ArrayList<>();
        }
        recieverList.add(new ChatObject(envName,messages));

        // adding primary env groups to list
        if(currentUSer.getPrimaryEnvironment().getGroups().size()>0){
            for(Group group : currentUSer.getPrimaryEnvironment().getGroups()){
                if(group.getAllowMessages()){
                    String groupName = group.getName();
                    List<Message> groupMessages;
                    if ( group.getMessages()!= null) {
                        groupMessages = group.getMessages();
                    } else {
                        groupMessages = new ArrayList<>();
                    }
                    recieverList.add(new ChatObject(envName,groupName,groupMessages));
                }
            }
        }

        // secondary env
        if (currentUSer.getSecondaryEnvironments().size()>0){
            for (Environment env : currentUSer.getSecondaryEnvironments()){

                // adding secondary env to list
                String secEnvName =  env.getEnvironmentName();
                List<Message> secMessages;
                if ( env.getMessages()!= null) {
                    secMessages = env.getMessages();
                } else {
                    secMessages = new ArrayList<>();
                }
                recieverList.add(new ChatObject(secEnvName,secMessages));

                // adding secondary env groups to list
                if(env.getGroups().size()>0){
                    for (Group group : env.getGroups()){
                        if(group.getAllowMessages()){
                            String groupName = group.getName();
                            List<Message> groupMessages;
                            if ( group.getMessages()!= null) {
                                groupMessages = group.getMessages();
                            } else {
                                groupMessages = new ArrayList<>();
                            }
                            recieverList.add(new ChatObject(secEnvName,groupName,groupMessages));
                        }
                    }
                }
            }
        }
    }
}
