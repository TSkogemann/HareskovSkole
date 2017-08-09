package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thsk.hareskovskole.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by thsk on 09/08/2017.
 */

public class MessageOverviewFragment extends Fragment {

    int fragmentResource;

    ArrayList<ChatObject> chatList;
    MessageOverviewAdapter adapter;
    ChatObject sendToName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_overview, container, false);
        ButterKnife.bind(this,view);
        fragmentResource = R.id.message_conversation;
        if (getArguments()!= null) {
            chatList = (ArrayList<ChatObject>) getArguments().getSerializable("chatList");
        }

        ListView listOfChatPartnersView = (ListView) view.findViewById(R.id.message_overview_list);
        adapter = new MessageOverviewAdapter(chatList,getActivity());
        listOfChatPartnersView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listOfChatPartnersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendToName = chatList.get(position);
                System.out.println("name picked " +sendToName);
                Bundle bundle = new Bundle();
                bundle.putSerializable("chat",sendToName);
                MessageConversationFragment msgFragment = new MessageConversationFragment();
                msgFragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("overview");
                ft.replace(fragmentResource, msgFragment);
                ft.commit();
            }
        });



        return view;
    }
}
