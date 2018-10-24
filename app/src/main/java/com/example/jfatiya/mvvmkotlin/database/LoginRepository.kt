package com.example.jfatiya.mvvmkotlin.database

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.text.TextUtils
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel
import com.example.jfatiya.mvvmkotlin.rest.NetworkUtils
import com.example.jfatiya.mvvmkotlin.rest.RxUtils
import com.google.gson.Gson
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Jay on 24-10-2018.
 */
class LoginRepository(application: Application) {

    private val loginDao: LoginDao
    val loginDetails: LiveData<LoginResponseModel>
    private val executor: Executor

    init {
        val db = DatabaseInstance.getInstance(application)
        this.loginDao = db.loginDao()
        this.loginDetails = loginDao.getLoginDetails()
        executor = Executors.newSingleThreadExecutor()
    }

    fun insert(model: LoginResponseModel) {
        InsertFormAsyncTask(loginDao).execute(model)
    }

    fun update(model: LoginResponseModel) {
        UpdateFormAsyncTask(loginDao).execute(model)
    }

    class InsertFormAsyncTask (private val loginDao: LoginDao) : AsyncTask<LoginResponseModel, Void, Void>() {

        override fun doInBackground(vararg models: LoginResponseModel): Void? {
            loginDao.insertLoginDetails(models[0])
            return null
        }
    }

    class UpdateFormAsyncTask (private val loginDao: LoginDao) : AsyncTask<LoginResponseModel, Void, Void>() {

        override fun doInBackground(vararg models: LoginResponseModel): Void? {
            loginDao.updateLoginDetails(models[0])
            return null
        }
    }

    @SuppressLint("CheckResult")
    fun loginUser(credentials: String) {

        NetworkUtils.getAPIService().login(credentials)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        { response: String ->
                            executor.execute {
                                try {
                                    if (!TextUtils.isEmpty(response)) {
                                        //val result = EncryptDecrypt.ToDecrypt(response)
                                        val gson = Gson()
                                        val jsonObject = JSONArray(result).getJSONArray(0).getJSONObject(0)
                                        val model = gson.fromJson<Any>(jsonObject.toString(), LoginResponseModel::class.java)
                                        model.setId(1)
                                        insert(model)
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        },
                        { e: Throwable -> e.printStackTrace() }
                )
    }
}