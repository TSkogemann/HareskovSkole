package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;

import butterknife.ButterKnife;

/**
 * Created by thsk on 07/07/2017.
 */

public class MessageConversationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_conversation, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
}
