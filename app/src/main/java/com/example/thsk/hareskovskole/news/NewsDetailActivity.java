package com.example.thsk.hareskovskole.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.thsk.hareskovskole.news.NewsItem.NewsItemType.ARTICLE;
import static com.example.thsk.hareskovskole.news.NewsItem.NewsItemType.COMMERCIAL;

/**
 * Created by thsk on 21/06/2017.
 */

public class NewsDetailActivity extends AppCompatActivity {

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

        switch (currentItem.getNewsItemType()){
            case ARTICLE:
                newsDetailAuthorTv.setText("Skrevet af: " + currentItem.getAuthor());
                break;
            case COMMERCIAL:
                newsDetailAuthorTv.setText(currentItem.getAuthor());
        }

            Glide.with(this)
                .load(currentItem.getMainPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(newsDetailPictureIv);
        newsDetailPictureTextTv.setText(currentItem.getMainPictureText());
        newsDetailHeadline.setText(currentItem.getHeadline());
        newsDetailMainTextTv.setText(currentItem.getMainText());
    }
}
