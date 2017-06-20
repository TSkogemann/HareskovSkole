package com.example.thsk.hareskovskole.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thsk.hareskovskole.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by thsk on 20/06/2017.
 */

public class NewsFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this,view);

        ArrayList<NewsItem> newsList  = getRandomNewsList();

        ListView newsView = (ListView)getActivity().findViewById(R.id.news_list);
        newsView.setAdapter(new NewsListAdapter(getActivity(), newsList));

        return view;
    }

    private ArrayList<NewsItem> getRandomNewsList() {
        String imageurl = "http://asset.dr.dk/imagescaler/?file=/images/other/2017/06/20/scanpix-20170620-153302-l.jpg&server=www.dr.dk&w=620&h=349&scaleAfter=crop&quality=75&ratio=16-9";
        ArrayList<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem("title1", "maintext1","feedtext1", imageurl));
        newsList.add(new NewsItem("title2", "maintext2","feedtext2", imageurl));
        newsList.add(new NewsItem("title3", "maintext3","feedtext3", imageurl));
        newsList.add(new NewsItem("title4", "maintext4","feedtext4", imageurl));
        newsList.add(new NewsItem("title5", "maintext5","feedtext5", imageurl));
        newsList.add(new NewsItem("title6", "maintext6","feedtext6", imageurl));
        return newsList;
    }


}
