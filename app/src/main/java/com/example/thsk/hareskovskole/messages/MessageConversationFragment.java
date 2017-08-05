package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.data.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 07/07/2017.
 */

public class MessageConversationFragment extends Fragment {

    @BindView(R.id.message_chat_list)
    ListView chat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_conversation, container, false);
        ButterKnife.bind(this,view);
        ChatObject chatObj;
        if (getArguments()!= null) {
            chatObj = (ChatObject) getArguments().getSerializable("chat");
            System.out.println(chat.toString());
        } else {
            chatObj = new ChatObject("error", new ArrayList<Message>());
        }

        ListView newsView = (ListView) view.findViewById(R.id.message_chat_list);
        newsView.setAdapter(new MessageListAdapter(chatObj.getChat(), getContext()));
       // chat.setAdapter(new MessageListAdapter(chatObj.getChat(), getContext()));
        return view;
    }
}
