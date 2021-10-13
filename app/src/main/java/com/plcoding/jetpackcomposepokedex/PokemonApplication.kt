package com.plcoding.jetpackcomposepokedex

import android.app.Application
import android.content.Context
import com.plcoding.jetpackcomposepokedex.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PokemonApplication : Application() {

    companion object {
        var appContext: PokemonApplication? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@PokemonApplication)
            modules(koinModules)
        }
    }
}