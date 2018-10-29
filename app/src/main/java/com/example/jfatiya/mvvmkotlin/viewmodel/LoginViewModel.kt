package com.example.jfatiya.mvvmkotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.example.jfatiya.mvvmkotlin.database.LoginRepository
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel
import com.example.jfatiya.mvvmkotlin.model.LoginRequestModel

import com.google.gson.Gson
//Don't try to get Android Specific classes
//for whatsoever reasons...eg : Context,Resources...!
//https://stackoverflow.com/questions/40475334/best-practice-for-android-mvvm-startactivity
public class LoginViewModel(application: Application,
                            var loginRepository: LoginRepository,
                            var LoginResponseModelMutableLiveData: MutableLiveData<LoginResponseModel>)
                             : AndroidViewModel(application)
{

    val username = ObservableField("")
    val password = ObservableField("")
    val isLoading = ObservableInt(8)
    val isLogging = ObservableInt(0)
    val usernameError = ObservableField<String>()
    val passwordError = ObservableField<String>()


    fun getLoginDetails(): MutableLiveData<LoginResponseModel> {
        return LoginResponseModelMutableLiveData
    }

    fun insert(model: LoginResponseModel) {
        loginRepository.insert(model)
    }

    fun update(model: LoginResponseModel) {
        loginRepository.update(model)
    }

    fun onLoginClicked() {
        if (validateInputs()) {
            isLoading.set(0)
            isLogging.set(8)

            //https://medium.com/meesho-tech/non-null-observablefield-in-kotlin-bd72d31ab54f
            loginRepository.loginUser(username.get()!!, password.get()!!)
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
}
