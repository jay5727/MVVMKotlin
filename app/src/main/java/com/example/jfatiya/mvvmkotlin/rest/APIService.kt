package com.example.jfatiya.mvvmkotlin.rest

import com.example.jfatiya.mvvmkotlin.model.LoginRequestModel
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("api/login")   // mocked login service
    abstract fun login(@Body request: LoginRequestModel): Observable<LoginResponseModel>

    //JAVA
    //@POST("api/login")   // mocked login service
    //Observable<LoginModel> login(@Body LoginRequestModel request);
}