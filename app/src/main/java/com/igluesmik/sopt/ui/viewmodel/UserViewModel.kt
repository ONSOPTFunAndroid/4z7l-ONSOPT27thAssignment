package com.igluesmik.sopt.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.repository.UserRepo
import com.igluesmik.sopt.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel(private val repo: UserRepo) : BaseViewModel() {

    private var _signIn = MutableLiveData<ResponseSignIn>()
    val signIn: LiveData<ResponseSignIn> = _signIn

    private var _signUp = MutableLiveData<ResponseSignUp>()
    val signUp: LiveData<ResponseSignUp> = _signUp

    fun signIn(email: String, password: String) {
        addDisposable(
            repo.signIn(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _signIn.postValue(it)
                }, {
                    Log.e(TAG, "signIn", it)
                })
        )
    }

    fun signUp(email: String, password: String, userName: String) {
        addDisposable(
            repo.signUp(email, password, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _signUp.postValue(it)
                }, {
                    Log.e(TAG, "signUp", it)
                })
        )
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }
}