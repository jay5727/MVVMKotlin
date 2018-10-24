package com.example.jfatiya.mvvmkotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*data class LoginResponseModel {
}*/
//Note that this is constructor itself & not the fields...
// AND EVERY DATA CLASS MUST HAVE ATLEAST ONE PRIMARY CONSTRUCTOR PARAMETER
@Entity(tableName = "login_table")
data class LoginResponseModel(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @SerializedName("token") val token: String,
        @SerializedName("password") var password: String
) {
    constructor(id: Int) : this(id, "", "")
}
