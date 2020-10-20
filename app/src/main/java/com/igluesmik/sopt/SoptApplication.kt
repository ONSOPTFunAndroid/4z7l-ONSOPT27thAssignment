package com.igluesmik.sopt

import android.app.Application
import android.content.SharedPreferences
import com.igluesmik.sopt.util.PreferenceUtil

class SoptApplication : Application(){
    companion object{
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        preferences = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}