package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.remote.datasource.UserRemoteDataSource
import io.reactivex.Single

class UserRepoImpl(private val userRemoteDataSource: UserRemoteDataSource) : UserRepo {
    override fun signIn(email: String, password: String): Single<ResponseSignIn> {
        return userRemoteDataSource.signIn(email, password)
    }

    override fun signUp(email: String, password: String, userName: String): Single<ResponseSignUp> {
        return userRemoteDataSource.signUp(email, password, userName)
    }
}