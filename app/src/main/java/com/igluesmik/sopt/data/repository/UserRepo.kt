package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.data.model.domain.User
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepo {
    fun signIn(email: String, password: String): Single<User>
    fun signUp(email: String, password: String, userName: String): Completable

    fun getUsers(): Single<List<Friend>>
}