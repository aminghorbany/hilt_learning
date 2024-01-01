package com.example.koin

import android.app.Application
import com.example.koin.interfacee.carModule
import com.example.koin.simple.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //koin
        startKoin {
            // add modules here

            //module context ->
            androidContext(this@MyApp)
            //simple
//            modules(userModule)

            //module Log ->
//            androidLogger() // for log

            //module interface
            modules(carModule)
        }
    }
}