package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import io.reactivex.Single

interface UserRemoteDataSource {
    fun signIn(email: String, password: String): Single<ResponseSignIn>
    fun signUp(email: String, password: String, userName: String): Single<ResponseSignUp>
}