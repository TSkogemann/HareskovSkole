package com.example.thsk.hareskovskole;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.thsk.hareskovskole.commercials.CommercialDialog;
import com.example.thsk.hareskovskole.utils.data.CommercialItem;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.news.NewsFragments;
import com.example.thsk.hareskovskole.utils.data.User;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.webservice.ApiClient;

import java.util.List;

import swagger.model.NewsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends MenuActivity {

    private int fragmentResource = R.id.home_main_content;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        currentUser = Utility.loadCurrentUser();
        applyFirstFragment();

        //show commercial
        showCommercial();

        // changes in the data structure caused this to crash, outcommented untill fixed
        // todo
        //testFecthingNewsOverNetwork();
    }

    // now it is just proof of concept, but later these data should be saved in Realm
    private void testFecthingNewsOverNetwork() {
        Log.d("NewsActivity", "token=" + currentUser.getLoginToken());
        ApiClient.getNewsApi().newsUsingGET().enqueue(new Callback<List<NewsItem>>() {
            @Override
            public void onResponse(Call<List<NewsItem>> call, Response<List<NewsItem>> response) {
                Log.d("NewsActivity", "code: " + response.code());
                for (NewsItem newsItem : response.body()) {
                    Log.d("NewsActivity", newsItem.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<NewsItem>> call, Throwable t) {
                Log.d("NewsActivity", t.getMessage());
                Toast.makeText(NewsActivity.this, "Netværks fejl!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showCommercial() {
        // get the right commercial
        int numberOfElements = currentUser.getMergedCommercials().size();
        CommercialItem commercialToBeShown = currentUser.getMergedCommercials().get(Utility.randomNumber(numberOfElements-1,0));

        // Create an instance of the dialog fragment and show it
        Dialog dialog = new CommercialDialog(this, commercialToBeShown);
        dialog.show();
    }

    private void applyFirstFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentResource, new NewsFragments());
        ft.addToBackStack(LoginFragment.class.getSimpleName());
        ft.commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
