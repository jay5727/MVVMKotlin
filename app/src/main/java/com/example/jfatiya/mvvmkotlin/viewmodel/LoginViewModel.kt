package com.example.jfatiya.mvvmkotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.example.jfatiya.mvvmkotlin.database.LoginRepository
import com.example.jfatiya.mvvmkotlin.model.LoginModel
import com.example.jfatiya.mvvmkotlin.model.LoginRequestModel
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel
import com.google.gson.Gson

public class LoginViewModel : AndroidViewModel {

    var loginRepository: LoginRepository
    var loginModelLiveData: LiveData<LoginResponseModel>

    val username = ObservableField("krishak5")
    val password = ObservableField("krishak123")
    val isLoading = ObservableInt(8)
    val isLogging = ObservableInt(0)
     val usernameError = ObservableField<String>()
     val passwordError = ObservableField<String>()

    constructor(application: Application, loginRepository: LoginRepository,
                loginModelLiveData: LiveData<LoginResponseModel>)
            : super(application) {
        this.loginRepository = loginRepository
        this.loginModelLiveData = loginModelLiveData
    }

    /* fun LoginViewModel(application: Application): ??? {
         //super(application)
         loginRepository = LoginRepository(application)
         loginModelLiveData = loginRepository.getLoginDetails()
     }*/



    fun getLoginDetails(): LiveData<LoginResponseModel> {
        return loginModelLiveData
    }

    fun insert(model: LoginModel) {
        loginRepository.insert(model)
    }

    fun update(model: LoginModel) {
        loginRepository.update(model)
    }

    fun onLoginClicked() {
        if (validateInputs()) {
            isLoading.set(0)
            isLogging.set(8)
            val model = LoginRequestModel(username.get()!!, password.get()!!)
            val gson = Gson()
            //val credentials = EncryptDecrypt.ToEncrypt(gson.toJson(model))
            loginRepository.loginUser(credentials)
        } else {
            isLoading.set(8)
            isLogging.set(0)
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (username.get() == null) {
            usernameError.set("Invalid username")
            isValid = false
        } else {
            usernameError.set(null)
        }

        if (password.get() == null || password.get()!!.length < 4) {
            passwordError.set("Password too short")
            isValid = false
        } else {
            passwordError.set(null)
        }
        return isValid
    }