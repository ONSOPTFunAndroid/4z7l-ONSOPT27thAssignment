package com.igluesmik.sopt.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val preference : SharedPreferences
    = context.getSharedPreferences("Sopt",Context.MODE_PRIVATE)

    fun getString(key : String, value : String) : String {
        return preference.getString(key, value).toString()
    }

    fun setString(key: String, value : String) {
        preference.edit().putString(key, value).apply()
    }

    fun getBoolean(key : String, value : Boolean) : Boolean {
        return preference.getBoolean(key, value)
    }

    fun setBoolean(key: String, value : Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }
}