package com.example.magiccards;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

public class RetrofitClient {

    static Context context;
    private static final String Base_url = "http://54.183.148.182/casino/public/api/";

    private static RetrofitClient instance;
    private Retrofit retrofit;
    static OkHttpClient.Builder httpClient;
    SharedPreferences sharedPreferences;
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();

        }
        return instance;
    }
    static public Context getContext() {
        return context;
    }

    private RetrofitClient() {

        httpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request;

              String token = null;
                if(token!=null) {

                    request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json").build();
                }else {

                    request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                }


                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .client(provideOkHttpClient())
                //  .header("accept", "application/json")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(provideHeaderInterceptor())
                .addInterceptor(new ForbiddenInterceptor())
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build();
    }

    public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("Injector", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return httpLoggingInterceptor;
    }
    public static Interceptor
    provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;



               String token=null;

                if(token!=null) {

                    request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json").build();
                }else {

                    request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                }

                return chain.proceed(request);
            }
        };
    }


    public Api_interface getapi() {
        return retrofit.create(Api_interface.class);

    }
    public static class ForbiddenInterceptor implements Interceptor {

        @Override
        public Response intercept(final Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (response.code() == 401 || response.code() == 403) {

            }
            return response;
        }
    }




}
