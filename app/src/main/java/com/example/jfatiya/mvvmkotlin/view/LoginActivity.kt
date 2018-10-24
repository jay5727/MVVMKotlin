package com.example.jfatiya.mvvmkotlin.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jfatiya.mvvmkotlin.R

class LoginActivity : AppCompatActivity() {

    private var loginViewModel: LoginViewModel? = null
    internal lateinit var binding: ActivityLoginBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Get the ViewModel.
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.setLoginmodel(loginViewModel)
    }
}
