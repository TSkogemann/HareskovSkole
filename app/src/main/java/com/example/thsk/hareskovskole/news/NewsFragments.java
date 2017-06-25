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

import com.example.thsk.hareskovskole.NewsActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.commercials.CommercialItem;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.Utility;

import java.util.ArrayList;
import java.util.List;

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

    ArrayList<NewsItem> newsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        setupNewsList();

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

    private void setupNewsList() {
        newsList = getRandomNewsList(15);
        List<NewsItem> commercialList = new ArrayList<>();
        for (CommercialItem com : NewsActivity.currentUser.getMergedCommercials()) {
            if (com.getNewsItem() != null) {
             commercialList.add(com.getNewsItem());
            }
        }
        for(int i=2; i < newsList.size() ; i+=3){
            if(commercialList.size() >0){
                int index = Utility.randomNumber(commercialList.size()-1,0);
                NewsItem commercialToAdd = commercialList.get(index);
                newsList.add(i,commercialToAdd);
                commercialList.remove(index);
            }
        }
    }

    private ArrayList<NewsItem> getRandomNewsList(int numberOfItems) {
        // This function is only used to generate random newslists
        ArrayList<NewsItem> newsList = new ArrayList<>();

        for (int i = 0; i < numberOfItems; i++) {
            newsList.add(Utility.getRandomNewsItem());
        }
        return newsList;
    }


}
