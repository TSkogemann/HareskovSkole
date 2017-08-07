package com.example.thsk.hareskovskole.news;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.thsk.hareskovskole.news.NewsItem.NewsItemType.ARTICLE;
import static com.example.thsk.hareskovskole.news.NewsItem.NewsItemType.COMMERCIAL;

public class NewsDetailActivity extends MenuActivity {

    @BindView(R.id.news_detail_title)
    TextView newsDetailTitleTv;
    @BindView(R.id.news_detail_author)
    TextView newsDetailAuthorTv;
    @BindView(R.id.news_detail_picture)
    ImageView newsDetailPictureIv;
    @BindView(R.id.news_detail_picture_text)
    TextView newsDetailPictureTextTv;
    @BindView(R.id.news_detail_headline)
    TextView newsDetailHeadline;
    @BindView(R.id.news_detail_main_text)
    TextView newsDetailMainTextTv;
    @BindView(R.id.news_detail_video)
    VideoView newsDetailVideoVv;

    @BindView(R.id.news_detail_back_button)
    Button newsDetailBackButton;

    NewsItem currentItem;

    // Layout should not be changed runtime. It needs to look the same every time.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        currentItem = (NewsItem) getIntent().getSerializableExtra("item");
        newsDetailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    private void initData() {
        newsDetailTitleTv.setText(currentItem.getTitle());

        switch (currentItem.getNewsItemType()) {
            case ARTICLE:
                newsDetailAuthorTv.setText("Skrevet af: " + currentItem.getAuthor());
                if (User.getUser().getPrimaryEnvironment().getPrimaryColor() != null) {
                    newsDetailTitleTv.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColor()));
                    newsDetailBackButton.setBackgroundColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColor()));
                }
                break;
            case COMMERCIAL:
                newsDetailAuthorTv.setText(currentItem.getAuthor());
                if (User.getUser().getPrimaryEnvironment().getPrimaryColorDark() != null) {
                    newsDetailTitleTv.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColorDark()));
                    newsDetailBackButton.setBackgroundColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColor()));
                }
        }

        if (currentItem.getMainVideo() != null) {
            showVideoView();

        } else {
            showImageView();
        }
            newsDetailPictureTextTv.setText(currentItem.getMainPictureText());
            newsDetailHeadline.setText(currentItem.getHeadline());
            newsDetailMainTextTv.setText(currentItem.getMainText());
    }

    private void showVideoView() {
        //setting video visible and removing imageview
        newsDetailVideoVv.setVisibility(View.VISIBLE);
        newsDetailPictureIv.setVisibility(View.GONE);

        newsDetailVideoVv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                showImageView();
                return true;
            }
        });

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(newsDetailVideoVv);
        Uri video = Uri.parse(currentItem.getMainVideo());
        newsDetailVideoVv.setMediaController(mediaController);
        newsDetailVideoVv.setVideoURI(video);
        newsDetailVideoVv.start();
    }

    private void showImageView() {
        //setting video visible and removing imageview
        newsDetailVideoVv.setVisibility(View.GONE);
        newsDetailPictureIv.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(currentItem.getMainPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(newsDetailPictureIv);
    }
}
