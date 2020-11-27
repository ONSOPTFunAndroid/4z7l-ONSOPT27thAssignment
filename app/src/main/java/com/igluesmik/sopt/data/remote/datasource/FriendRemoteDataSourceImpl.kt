package com.igluesmik.sopt.data.remote.datasource

import com.igluesmik.sopt.data.model.network.response.ResponseUsers
import com.igluesmik.sopt.data.remote.api.FriendService
import com.igluesmik.sopt.data.remote.api.UserService
import io.reactivex.Single

class FriendRemoteDataSourceImpl(private val service: FriendService): FriendRemoteDataSource {
    override fun getUsers(): Single<ResponseUsers> {
        return service.getUsers()
    }
}