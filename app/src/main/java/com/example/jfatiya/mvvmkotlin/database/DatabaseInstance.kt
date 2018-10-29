package com.example.jfatiya.mvvmkotlin.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.example.jfatiya.mvvmkotlin.model.LoginResponseModel

/**
 * Created by Jay on 24-10-2018.
 */
@Database(entities = arrayOf(LoginResponseModel::class), version = 1)
abstract class DatabaseInstance : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    private class PopulateFirstTimeAsync

    (db: DatabaseInstance?) : AsyncTask<Void, Void, Void>()
    {

        private lateinit var loginDao: LoginDao

        init {
            if (db != null) {
                loginDao = db.loginDao()
            }
        }

        override fun doInBackground(vararg voids: Void): Void? {
            loginDao.insertLoginDetails(LoginResponseModel(1))
            return null
        }
    }

    companion object
    {

        @Volatile private var instance: DatabaseInstance? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseInstance {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, DatabaseInstance::class.java,
                        "mvvmkotlin_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build()
            }
            return instance as DatabaseInstance
        }

        private val callback = object : RoomDatabase.Callback()
        {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateFirstTimeAsync(instance).execute()
            }
        }
    }
}