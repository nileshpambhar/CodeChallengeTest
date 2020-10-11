package com.mobiquitytest

import android.app.Application
import com.mobiquitytest.demo.data.room.AppDatabase
/**
 * Created by Nilesh Pambhar
 */
class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
            private set
    }

    lateinit var appDatabase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        instance = this
        appDatabase = AppDatabase.getInstance(this)
    }

}