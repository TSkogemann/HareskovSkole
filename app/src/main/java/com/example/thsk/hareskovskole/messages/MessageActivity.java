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

    private int fragmentResource;

    User currentUSer;
    List<ChatObject> recieverList;
    private ChatObject sendToName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        fragmentResource = R.id.message_conversation;
        currentUSer = Utility.loadCurrentUser();

        // init spinner
        initListOfRecievers();
        applyFirstFragment();
    }

    private void applyFirstFragment() {
        //not adding this transaction to backStack
        Bundle bundle = new Bundle();

        //cannot parse a normal list so we need to convert to arraylist
        ArrayList<ChatObject> temp = new ArrayList<>();
        for (ChatObject obj : recieverList) {
            temp.add(obj);
        }

        bundle.putSerializable("chatList", temp);
        MessageOverviewFragment msgFragment = new MessageOverviewFragment();
        msgFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, msgFragment);
        ft.addToBackStack(MessageOverviewFragment.class.getSimpleName());
        ft.commit();
    }


    private void initListOfRecievers() {
        recieverList = new ArrayList<>();

        // adding primary env to list
        //name
        String envName = currentUSer.getPrimaryEnvironment().getEnvironmentName();
        //messages
        List<Message> messages;
        if (currentUSer.getPrimaryEnvironment().getMessages() != null) {
            messages = currentUSer.getPrimaryEnvironment().getMessages();
        } else {
            messages = new ArrayList<>();
        }
        ChatObject obj = new ChatObject(envName,messages);
        //logo
        if (currentUSer.getPrimaryEnvironment().getLogo()!= null){
            obj.setLogo(currentUSer.getPrimaryEnvironment().getLogo());
        }

        recieverList.add(obj);

        // adding primary env groups to list
        if (currentUSer.getPrimaryEnvironment().getGroups().size() > 0) {
            for (Group group : currentUSer.getPrimaryEnvironment().getGroups()) {
                if (group.getAllowMessages()) {
                    //name
                    String groupName = group.getName();
                    //messages
                    List<Message> groupMessages;
                    if (group.getMessages() != null) {
                        groupMessages = group.getMessages();
                    } else {
                        groupMessages = new ArrayList<>();
                    }
                    ChatObject obj2 = new ChatObject(envName,groupName,groupMessages);
                    //logo
                    if (group.getLogo() != null){
                        obj2.setLogo(group.getLogo());
                    }
                    recieverList.add(obj2);
                }
            }
        }

        // secondary env
        if (currentUSer.getSecondaryEnvironments().size() > 0) {
            for (Environment env : currentUSer.getSecondaryEnvironments()) {

                // adding secondary env to list
                //name
                String secEnvName = env.getEnvironmentName();
                //messages
                List<Message> secMessages;
                if (env.getMessages() != null) {
                    secMessages = env.getMessages();
                } else {
                    secMessages = new ArrayList<>();
                }
                ChatObject obj3 = new ChatObject(secEnvName,secMessages);
                //logo
                if(env.getLogo() != null){
                    obj3.setLogo(env.getLogo());
                }
                recieverList.add(obj3);

                // adding secondary env groups to list
                if (env.getGroups().size() > 0) {
                    for (Group group : env.getGroups()) {
                        if (group.getAllowMessages()) {
                            //name
                            String groupName = group.getName();
                            //messages
                            List<Message> groupMessages;
                            if (group.getMessages() != null) {
                                groupMessages = group.getMessages();
                            } else {
                                groupMessages = new ArrayList<>();
                            }
                            ChatObject obj4 = new ChatObject(secEnvName,groupName,groupMessages);
                            if (group.getLogo() != null){
                                obj4.setLogo(group.getLogo());
                            }
                            recieverList.add(obj4);
                        }
                    }
                }
            }
        }
    }
}
