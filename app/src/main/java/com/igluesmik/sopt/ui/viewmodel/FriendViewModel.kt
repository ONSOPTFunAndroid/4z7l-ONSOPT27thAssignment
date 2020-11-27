package com.igluesmik.sopt.ui.viewmodel

import com.igluesmik.sopt.data.repository.UserRepo
import com.igluesmik.sopt.ui.base.BaseViewModel

class FriendViewModel(private val repo: UserRepo): BaseViewModel() {

    fun getUsers() {
        addDisposable(repo.getUsers()
            .subscribe ({

            }, {

            })
        )
    }

}