package com.meksconway.theweather.base

import android.app.Application
import com.meksconway.theweather.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application(){

    companion object{
        lateinit var instance: WeatherApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(
                networkingModule,
                interactionModule,
                databaseModule,
                repositoryModule,
                appModule
                ))
        }

    }




}