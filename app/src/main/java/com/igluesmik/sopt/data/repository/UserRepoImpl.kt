package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.remote.UserService
import io.reactivex.Single

class UserRepoImpl(private val userRemoteDataSource: UserService): UserRepo {
    override fun signIn(body: RequestSignIn): Single<ResponseSignIn>
        = userRemoteDataSource.signIn(body)

    override fun signUp(body: RequestSignUp): Single<ResponseSignUp>
        = userRemoteDataSource.signUp(body)
}