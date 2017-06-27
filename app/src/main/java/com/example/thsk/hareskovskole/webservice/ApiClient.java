package com.example.thsk.hareskovskole.webservice;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Authentication", "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJTVFVERU5UIl0sInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6ImtseXZlciJ9.k5vAsqnefabQ8l-qDPoB112czskzrLz08d4VOofRTlEGLhloisvqw_X1XH21Az1WxmjLqnWMtS9HMu6Wljeyug").build();
                    return chain.proceed(request);
                }
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://sunlit-apricot-171716.appspot.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApi() {
        if (apiInterface == null) {
            apiInterface = getRetrofit().create(ApiInterface.class);
        }
        return apiInterface;
    }

}