package com.example.jfatiya.mvvmkotlin.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
object ApiClient {

    val BASE_URL = "http://www.mocky.io/"

    private lateinit var retrofit: Retrofit;

    //added
    val client: Retrofit
        get() {
            //if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(customLogInterceptor())
                    .build()
            //}
            return retrofit
        }

    private fun customLogInterceptor(): okhttp3.OkHttpClient {
        //TO LOG RETROFIT API CALLS
        val loggingInterceptor = HttpLoggingInterceptor(CustomHttpLogging())
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttpClient = OkHttpClient()

        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(loggingInterceptor)//
                .connectTimeout(100, TimeUnit.SECONDS)//
                .readTimeout(100, TimeUnit.SECONDS)//
                .build()

        return okHttpClient
    }
}
*/
//https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html
//if you don't need companion object,replace class as object & comment companion object {
//also note that object is not the base class of Kotlin as we have in  Java - >java.lang.Object...!
class ApiClient {
    companion object ApiClient {

        //define static functions here
        val BASE_URL = "http://www.mocky.io/"

        fun getClient(): APIService
        {
            val retrofit = Retrofit.Builder()
                    .client(customLogInterceptor())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(APIService::class.java)
        }

        private fun customLogInterceptor(): okhttp3.OkHttpClient {
            //TO LOG RETROFIT API CALLS
            val loggingInterceptor = HttpLoggingInterceptor(CustomHttpLogging())
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            var okHttpClient = OkHttpClient()

            okHttpClient = okHttpClient.newBuilder()
                    .addInterceptor(loggingInterceptor)//
                    .connectTimeout(100, TimeUnit.SECONDS)//
                    .readTimeout(100, TimeUnit.SECONDS)//
                    .build()

            return okHttpClient
        }
    }
}