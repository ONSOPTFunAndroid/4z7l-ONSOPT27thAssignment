package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.response.ResponseUsers
import io.reactivex.Single

interface FriendRemoteDataSource {
    fun getUsers(): Single<ResponseUsers>
}