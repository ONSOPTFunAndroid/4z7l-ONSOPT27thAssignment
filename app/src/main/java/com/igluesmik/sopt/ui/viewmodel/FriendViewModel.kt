package com.igluesmik.sopt.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.data.repository.UserRepo
import com.igluesmik.sopt.ui.base.BaseViewModel

class FriendViewModel(private val repo: UserRepo): BaseViewModel() {

    private var _friendList = MutableLiveData<List<Friend>>()
    val friendList: LiveData<List<Friend>> = _friendList

    fun getUsers() {
        addDisposable(repo.getUsers()
            .subscribe ({
                _friendList.postValue(it)
            }, {
                Log.e(TAG, "getUsers", it)
            })
        )
    }

    companion object{
        private val TAG = FriendViewModel::class.java.simpleName
    }
}