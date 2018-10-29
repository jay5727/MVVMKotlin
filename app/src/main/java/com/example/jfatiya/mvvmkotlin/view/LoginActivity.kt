package com.example.jfatiya.mvvmkotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jfatiya.mvvmkotlin.R
import com.example.jfatiya.mvvmkotlin.databinding.ActivityLoginBinding
import com.example.jfatiya.mvvmkotlin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    //private var loginViewModel: LoginViewModel? = null
    private lateinit var loginViewModel: LoginViewModel
    internal lateinit var binding: ActivityLoginBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_login) as ActivityLoginBinding

        // Get the ViewModel.
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewmodel = loginViewModel
        loginViewModel!!.getLoginDetails().observe(this, Observer {
            val y: String = (it?.token) ?: ""
            Toast.makeText(this, y, Toast.LENGTH_LONG).show();
        })
    }
}
