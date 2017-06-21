package com.example.thsk.hareskovskole.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.thsk.hareskovskole.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 21/06/2017.
 */

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.news_detail_title)
    TextView newsDetailTitleTv;
    @BindView(R.id.news_detail_main_text)
    TextView newsDetailMainTextTv;

    NewsItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        currentItem = (NewsItem) getIntent().getSerializableExtra("item");
        newsDetailTitleTv.setText(currentItem.getTitle());
        if(currentItem.getTitle().length()%2 == 0) {
            newsDetailTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 76);
        }
        newsDetailMainTextTv.setText(currentItem.getMainText());
    }
}
