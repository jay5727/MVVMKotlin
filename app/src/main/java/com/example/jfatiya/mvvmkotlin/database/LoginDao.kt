package com.example.jfatiya.mvvmkotlin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel

/**
 * Created by Jay on 24-10-2018.
 */
@Dao
interface LoginDao {

    @Query("SELECT * FROM login_table")
    fun getLoginDetails(): LiveData<LoginResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginDetails(model: LoginResponseModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLoginDetails(model: LoginResponseModel)

}