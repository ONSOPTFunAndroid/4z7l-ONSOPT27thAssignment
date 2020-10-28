package com.igluesmik.sopt

import android.app.Application
import com.igluesmik.sopt.di.DiModule
import com.igluesmik.sopt.util.PreferenceUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SoptApplication : Application(){
    companion object{
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceUtil(applicationContext)
        startKoin {
            androidContext(applicationContext)
            modules(DiModule)
        }
    }
}