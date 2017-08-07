package com.example.thsk.hareskovskole.commercials;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 23/06/2017.
 */

public class CommercialDetailActivity extends MenuActivity {

    @BindView(R.id.commercial_detail_title)
    TextView commercialDetailTitle;
    @BindView(R.id.commercial_detail_text)
    TextView commercialDetailText;
    @BindView(R.id.commercial_detail_picture_pager)
    ViewPager commercialDetailPicturePager;
    @BindView(R.id.commercial_detail_video)
    VideoView commercialDetailVideo;

    CommercialItem currentItem;
    PagerAdapter pageAdapter;
    List<String> pictures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_detail);
        ButterKnife.bind(this);
        currentItem = (CommercialItem) getIntent().getSerializableExtra("commercial");
        setData();

        //iniate viewpager and adapter
        pageAdapter = new ScreenSliderAdapter(getApplicationContext(), pictures);
        commercialDetailPicturePager.setAdapter(pageAdapter);

    }

    private void setData() {
        // title
        commercialDetailTitle.setText(currentItem.getCommercialDetailTitle());
        if (User.getUser().getPrimaryEnvironment().getPrimaryColor() != null) {
            commercialDetailTitle.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColorDark()));
        }

        // pictures and video
        if (currentItem.getCommercialDetailVideo() != null) {
            showVideo();
        } else {
            showPictures();
        }

        // text
        commercialDetailText.setText(currentItem.getCommercialDetailText());
    }

    private void showVideo() {
        commercialDetailVideo.setVisibility(View.VISIBLE);
        commercialDetailPicturePager.setVisibility(View.GONE);

        commercialDetailVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                showPictures();
                return true;
            }
        });

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(commercialDetailVideo);
        Uri video = Uri.parse(currentItem.getCommercialDetailVideo());
        commercialDetailVideo.setMediaController(mediaController);
        commercialDetailVideo.setVideoURI(video);
        commercialDetailVideo.start();
    }

    private void showPictures() {
        commercialDetailPicturePager.setVisibility(View.VISIBLE);
        commercialDetailVideo.setVisibility(View.GONE);

        pictures.add(currentItem.getCommercialDetailPicture());
        for (String pic : currentItem.getDialogDetailExtraPictures()) {
            pictures.add(pic);
        }
    }
}
