package com.igluesmik.sopt.ui.view.login

interface UserListener {
    fun onLoginSuccess()
    fun onRegisterSuccess()
    fun onFailure()
}