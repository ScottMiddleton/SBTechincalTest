package com.example.sbtechincaltest

import android.app.Application
import com.example.sbtechincaltest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class SBApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SBApplication)
            modules(provideDependency())
        }
    }

    open fun provideDependency() = appModule
}