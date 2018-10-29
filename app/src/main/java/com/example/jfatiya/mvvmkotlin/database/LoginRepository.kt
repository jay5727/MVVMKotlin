package com.example.jfatiya.mvvmkotlin.database

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.text.TextUtils
import android.widget.Toast
import com.example.jfatiya.mvvmkotlin.model.LoginRequestModel
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel
import com.example.jfatiya.mvvmkotlin.rest.ApiClient
import com.example.jfatiya.mvvmkotlin.rest.NetworkUtils
/*import com.example.jfatiya.mvvmkotlin.rest.RxUtils*/
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/*
Created by Jay on 24-10-2018.
*/

class LoginRepository(application: Application) {

    private val loginDao: LoginDao
    val loginDetails: LiveData<LoginResponseModel>
    //private val executor: Executor

    init {
        val db = DatabaseInstance.getInstance(application)
        this.loginDao = db.loginDao()
        this.loginDetails = loginDao.getLoginDetails()
        //executor = Executors.newSingleThreadExecutor()
    }

    fun insert(model: LoginResponseModel) {
        InsertFormAsyncTask(loginDao).execute(model)
    }

    fun update(model: LoginResponseModel) {
        UpdateFormAsyncTask(loginDao).execute(model)
    }

    class InsertFormAsyncTask(private val loginDao: LoginDao) : AsyncTask<LoginResponseModel, Void, Void>() {

        override fun doInBackground(vararg models: LoginResponseModel): Void? {
            loginDao.insertLoginDetails(models[0])
            return null
        }
    }

    class UpdateFormAsyncTask(private val loginDao: LoginDao) : AsyncTask<LoginResponseModel, Void, Void>() {

        override fun doInBackground(vararg models: LoginResponseModel): Void? {
            loginDao.updateLoginDetails(models[0])
            return null
        }
    }

    fun loginUser(emailId: String, password: String) {
        //ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        val apiService = ApiClient.getClient()
        /*val disposable = */apiService.login(LoginRequestModel(emailId, password))
                //.compose()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.newThread())
                //.observeOn(Schedulers.computation())
                .subscribe(
                        {
                           result ->
                            showResults(result.token);
                        },
                        {
                            error->
                        })
    }

    private fun showResults(token :String) {
      val x:Int=5
    }
}
