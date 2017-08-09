package com.example.thsk.hareskovskole.overview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Group;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 07/08/2017.
 */

public class OverviewDetailActivity extends MenuActivity {

    @BindView(R.id.overview_detail_title)
    TextView title;
    @BindView(R.id.overview_detail_picture)
    ImageView picture;
    @BindView(R.id.overview_detail_description)
    TextView description;
    @BindView(R.id.overview_detail_contact_name)
    TextView contactName;
    @BindView(R.id.overview_detail_contact_details)
    TextView contactDetails;
    @BindView(R.id.overview_detail_lokation_info)
    TextView locationInfo;

    Group currentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_detail);
        ButterKnife.bind(this);
        currentGroup = (Group) getIntent().getSerializableExtra("item");
        initData();
    }

    private void initData() {

        // setting text
        title.setText(currentGroup.getName());
        description.setText(currentGroup.getDescription());
        contactName.setText(currentGroup.getContactName());
        contactDetails.setText(currentGroup.getContactDetails());
        locationInfo.setText(currentGroup.getLocation());

        // fonts and colors
        title.setTextColor(Utility.stringToColor(Utility.loadCurrentUser().getPrimaryEnvironment().getPrimaryColorDark()));

        // setting picture
        Glide.with(this)
                .load(currentGroup.getDescriptionPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(picture);
    }
}
