package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.data.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thsk on 07/07/2017.
 */

public class MessageConversationFragment extends Fragment {

    @BindView(R.id.message_chat_list)
    ListView chat;
    @BindView(R.id.message_send_msg_button)
    Button sendBtn;
    @BindView(R.id.message_input_et)
    EditText messageEt;
    MessageListAdapter adapter;
    ChatObject chatObj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_conversation, container, false);
        ButterKnife.bind(this,view);
        if (getArguments()!= null) {
            chatObj = (ChatObject) getArguments().getSerializable("chat");
        } else {
            chatObj = new ChatObject("error", new ArrayList<Message>());
        }

        ListView newsView = (ListView) view.findViewById(R.id.message_chat_list);
        adapter = new MessageListAdapter(chatObj.getChat(),getActivity());
        newsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        return view;
    }

    @OnClick (R.id.message_send_msg_button)
    public void sendbtnClicked (){
        String text;
        text = messageEt.getText().toString();
        Toast.makeText(MessageConversationFragment.this.getContext(), "Send clicked with message: " + text, Toast.LENGTH_LONG).show();
    }
}
