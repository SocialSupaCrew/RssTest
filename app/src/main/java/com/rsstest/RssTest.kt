package com.rsstest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class RssTest : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@RssTest)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(appModule)
        }
    }
}
