package com.example.imageloading.webapi;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static Context ctx;

    private static WebApi REST_CLIENT;

    public RetrofitClient(Context ctx) {
        this.ctx = ctx;
        setupRestClient();
    }

    public static WebApi get() {
        if (null != REST_CLIENT) {
            return REST_CLIENT;
        } else {
            setupRestClient();
            return REST_CLIENT;
        }
    }

    public static void setupRestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebApi.API)
                .client(getOkHttpClient(ctx))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        REST_CLIENT = retrofit.create(WebApi.class);
    }

    public static OkHttpClient getOkHttpClient(Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

        okClientBuilder.addInterceptor(httpLoggingInterceptor);

        okClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return okClientBuilder.build();
    }
}
