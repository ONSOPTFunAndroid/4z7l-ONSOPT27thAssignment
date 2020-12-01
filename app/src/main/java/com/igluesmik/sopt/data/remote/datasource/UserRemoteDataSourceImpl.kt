package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.remote.api.UserService
import io.reactivex.Single

class UserRemoteDataSourceImpl(private val service: UserService) : UserRemoteDataSource {
    override fun signIn(email: String, password: String): Single<ResponseSignIn> {
        return service.signIn(RequestSignIn(email, password))
    }

    override fun signUp(email: String, password: String, userName: String): Single<ResponseSignUp> {
        return service.signUp(RequestSignUp(email, password, userName))
    }
}