package com.example.thsk.hareskovskole.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thsk.hareskovskole.HomeActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by thsk on 20/06/2017.
 */

public class NewsFragments extends Fragment {

    @BindView(R.id.news_titel)
    TextView newsFeedTitleTv;
    @BindView(R.id.news_feed_text)
    TextView newsFeedTextTv;
    @BindView(R.id.news_feed_picture)
    ImageView newsFeedPictureIv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        final ArrayList<NewsItem> newsList = getRandomNewsList();

        ListView newsView = (ListView) getActivity().findViewById(R.id.news_list);
        newsView.setAdapter(new NewsListAdapter(getActivity(), newsList));

        newsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = "position " + position;
                System.out.println(temp);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("item", newsList.get(position));
                startActivity(intent);

            }
        });

        return view;
    }

    private ArrayList<NewsItem> getRandomNewsList() {
        // This function is only used to generate random newslists
        ArrayList<NewsItem> newsList = new ArrayList<>();

        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        newsList.add(getRandomNewsItem());
        return newsList;
    }

    private NewsItem getRandomNewsItem() {

        String title = Utility.randomText(4, 1);
        String mainText = Utility.randomText(400, 0);
        String feedText = Utility.randomText(150, 0);
        String picture = Utility.randomPicture();
        return new NewsItem(title, mainText, feedText, picture);
    }


}
