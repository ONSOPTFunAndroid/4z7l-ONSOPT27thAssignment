package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.entity.Friend
import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseUsers
import io.reactivex.Single

interface UserRepo {
    fun signIn(email: String, password: String): Single<ResponseSignIn>
    fun signUp(email: String, password: String, userName: String): Single<ResponseSignUp>

    fun getUsers(): Single<List<Friend>>
}