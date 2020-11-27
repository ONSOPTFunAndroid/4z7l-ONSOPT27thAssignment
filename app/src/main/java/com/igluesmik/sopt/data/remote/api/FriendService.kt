package com.igluesmik.sopt.data.remote.api

import com.igluesmik.sopt.data.model.network.response.ResponseUsers
import io.reactivex.Single
import retrofit2.http.GET

interface FriendService {
    @GET("/api/users")
    fun getUsers(): Single<ResponseUsers>
}