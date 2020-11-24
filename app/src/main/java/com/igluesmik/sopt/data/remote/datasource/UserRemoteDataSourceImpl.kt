package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.remote.api.UserService
import io.reactivex.Single

class UserRemoteDataSourceImpl(private val service: UserService): UserRemoteDataSource {
    override fun signIn(body: RequestSignIn): Single<ResponseSignIn> {
        return service.signIn(body)
    }

    override fun signUp(body: RequestSignUp): Single<ResponseSignUp> {
        return service.signUp(body)
    }
}