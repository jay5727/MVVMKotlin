package com.example.jfatiya.mvvmkotlin.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    val BASE_URL = "https://reqres.in/api/login";
    val apiService: APIService
        get() {
            val retrofit = Retrofit.Builder()

            retrofit
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.build().create(APIService::class.java)
        }
}
/*
public class NetworkUtils {

    public static APIService getAPIService() {
        Retrofit.Builder retrofit = new Retrofit.Builder();

        retrofit
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.build().create(APIService.class);
    }
}*/
