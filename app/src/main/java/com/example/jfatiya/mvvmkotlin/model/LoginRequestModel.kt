package com.example.jfatiya.mvvmkotlin.model
import com.google.gson.annotations.SerializedName


//Note that this is constructor itself & not the fields...
// AND EVERY DATA CLASS MUST HAVE ATLEAST ONE PRIMARY CONSTRUCTOR PARAMETER
data class LoginRequestModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)