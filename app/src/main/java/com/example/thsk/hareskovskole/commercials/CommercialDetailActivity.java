package com.example.thsk.hareskovskole.commercials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.news.NewsItem;

/**
 * Created by thsk on 23/06/2017.
 */

public class CommercialDetailActivity extends AppCompatActivity {

    CommercialItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_detail);
        currentItem = (CommercialItem) getIntent().getSerializableExtra("item");

    }
}
