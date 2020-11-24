package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import io.reactivex.Single

interface UserRemoteDataSource {
    fun signIn(body: RequestSignIn): Single<ResponseSignIn>
    fun signUp(body: RequestSignUp): Single<ResponseSignUp>
}