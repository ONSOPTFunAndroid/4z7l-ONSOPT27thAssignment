package com.igluesmik.sopt.util

import com.igluesmik.sopt.SoptApplication

object LoginPreference {

    fun isAutoLoginSet() : Boolean =
        SoptApplication.preferences.getBoolean("auto_login", false)

    fun setAutoLogin(boolean: Boolean) {
        SoptApplication.preferences.setBoolean("auto_login", boolean)
    }

    fun setUserPreference(id : String, password : String) {
        SoptApplication.preferences.setBoolean("auto_login",true)
        SoptApplication.preferences.setString("id",id)
        SoptApplication.preferences.setString("password",password)
    }

}