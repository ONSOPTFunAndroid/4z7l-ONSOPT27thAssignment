package com.igluesmik.sopt.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igluesmik.sopt.data.model.domain.User
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.repository.UserRepo
import com.igluesmik.sopt.ui.base.BaseViewModel
import com.igluesmik.sopt.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.function.Consumer

class UserViewModel(private val repo: UserRepo) : BaseViewModel() {

    private val _signInTaskEvent = MutableLiveData<Event<Any>>()
    val signInTaskEvent: LiveData<Event<Any>> = _signInTaskEvent

    private val _signUpTaskEvent = MutableLiveData<Event<Any>>()
    val signUpTaskEvent: LiveData<Event<Any>> = _signUpTaskEvent

    fun signIn(email: String, password: String) {
        addDisposable(
            repo.signIn(email,password)
                .subscribe({
                    _signInTaskEvent.postValue(Event(it))
                }, {
                    _signInTaskEvent.postValue(Event("로그인 실패"))
                    Log.v(TAG, "signIn", it)
                })
        )
    }

    fun signUp(email: String, password: String, userName: String) {
        addDisposable(
            repo.signUp(email, password, userName)
                .subscribe({
                    _signUpTaskEvent.postValue(Event(it))
                }, {
                    _signUpTaskEvent.postValue(Event("회원가입 실패"))
                    Log.v(TAG, "signUp", it)
                })
        )
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }
}