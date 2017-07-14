package com.example.thsk.hareskovskole.webservice;


import android.util.Log;

import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.realm.RealmUser;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmResults;
import swagger.api.MessageResourceApi;
import swagger.api.NewsResourceApi;
import swagger.api.UserResourceApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static NewsResourceApi newsApi = null;
    private static UserResourceApi userApi = null;
    private static MessageResourceApi messageApi = null;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    Realm myRealm = Realm.getDefaultInstance();
                    myRealm.refresh();
                    RealmResults<RealmUser> realmUsers = myRealm.where(RealmUser.class).findAll();
                    if (realmUsers.size() > 0 && realmUsers.get(0).getLoginToken() != null) {
                        requestBuilder.addHeader("Authentication", realmUsers.get(0).getLoginToken());
                    }
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://sunlit-apricot-171716.appspot.com")
//                    .baseUrl("http://10.0.2.2:8080")  // for debugging, when you have the backend running on your own laptop.
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static NewsResourceApi getNewsApi() {
        if (newsApi == null) {
            newsApi = getRetrofit().create(NewsResourceApi.class);
        }
        return newsApi;
    }

    public static UserResourceApi getUserApi() {
        if (userApi == null) {
            userApi = getRetrofit().create(UserResourceApi.class);
        }
        return userApi;
    }

    public static MessageResourceApi getMessageApi() {
        if (messageApi == null) {
            messageApi = getRetrofit().create(MessageResourceApi.class);
        }
        return messageApi;
    }

}