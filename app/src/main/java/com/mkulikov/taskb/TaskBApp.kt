package com.mkulikov.taskb

import android.app.Application
import com.mkulikov.taskb.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TaskBApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TaskBApp)
            modules(commonModule)
        }
    }
}