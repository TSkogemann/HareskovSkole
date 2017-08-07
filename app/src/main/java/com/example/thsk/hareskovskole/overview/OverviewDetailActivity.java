package com.example.thsk.hareskovskole.overview;

import android.os.Bundle;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.data.Group;

import butterknife.ButterKnife;

/**
 * Created by thsk on 07/08/2017.
 */

public class OverviewDetailActivity extends MenuActivity {

    Group currentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_detail);
        ButterKnife.bind(this);
        currentGroup = (Group) getIntent().getSerializableExtra("item");
    }
}
