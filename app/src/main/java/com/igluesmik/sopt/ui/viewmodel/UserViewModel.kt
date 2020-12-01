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

    private val _signInTaskEvent = MutableLiveData<Event<User>>()
    val signInTaskEvent: LiveData<Event<User>> = _signInTaskEvent

    private val _signUpTaskEvent = MutableLiveData<Event<Boolean>>()
    val signUpTaskEvent: LiveData<Event<Boolean>> = _signUpTaskEvent

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    fun signIn(email: String, password: String) {
        addDisposable(
            repo.signIn(email,password)
                .subscribe({
                    _signInTaskEvent.postValue(Event(it))
                    _toastMessage.postValue(Event("로그인 성공"))
                }, {
                    _toastMessage.postValue(Event("로그인 실패"))
                    Log.v(TAG, "signIn", it)
                })
        )
    }

    fun signUp(email: String, password: String, userName: String) {
        addDisposable(
            repo.signUp(email, password, userName)
                .subscribe({
                    _signUpTaskEvent.postValue(Event(true))
                    _toastMessage.postValue(Event("회원가입 성공"))
                }, {
                    _toastMessage.postValue(Event("회원가입 실패"))
                    Log.v(TAG, "signUp", it)
                })
        )
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }
}