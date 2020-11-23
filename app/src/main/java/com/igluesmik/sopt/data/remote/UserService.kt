package com.igluesmik.sopt.data.remote

import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST("/users/signin")
    @Headers("Content-Type: application/json")
    fun signIn(
        @Body body: RequestSignIn
    ): Single<ResponseSignIn>

    @POST("/users/signup")
    @Headers("Content-Type: application/json")
    fun signUp(
        @Body body: RequestSignUp
    ): Single<ResponseSignUp>
}