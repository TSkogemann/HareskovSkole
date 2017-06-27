package com.example.thsk.hareskovskole.webservice;

import com.example.thsk.hareskovskole.news.NewsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/news")
    Call<List<NewsItem>> getNewsItems();

    @GET("/api/commercial")
    Call<List<NewsItem>> getCommercials();

}
