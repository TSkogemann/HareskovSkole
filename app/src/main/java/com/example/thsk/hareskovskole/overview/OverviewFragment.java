package com.example.thsk.hareskovskole.overview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Environment;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by thsk on 07/08/2017.
 */

public class OverviewFragment extends Fragment {

    ArrayList<Group> groupList = new ArrayList<>();
    User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        currentUser = Utility.loadCurrentUser();
        setupGroupList();

        ListView newsView = (ListView) getActivity().findViewById(R.id.overview_list);
        OverviewListAdapter adapter = new OverviewListAdapter(getActivity(),groupList);
        newsView.setAdapter(adapter);

        newsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = "position " + position;
                System.out.println(temp);
                Intent intent = new Intent(getActivity(), OverviewDetailActivity.class);
                intent.putExtra("item", groupList.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupGroupList() {

        // getting groups from primary env
        for (Group group : currentUser.getPrimaryEnvironment().getGroups()){
            if(group.getShownInOverview()){
                groupList.add(group);
            }
        }
        // getting groups from secondary env
        for (Environment env : currentUser.getSecondaryEnvironments()){
            for (Group group : env.getGroups()){
                if(group.getShownInOverview()){
                    groupList.add(group);
                }
            }
        }
    }

}
