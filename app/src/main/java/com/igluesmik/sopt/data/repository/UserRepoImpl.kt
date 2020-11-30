package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.entity.Friend
import com.igluesmik.sopt.data.model.network.request.RequestSignIn
import com.igluesmik.sopt.data.model.network.request.RequestSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.model.network.response.ResponseUsers
import com.igluesmik.sopt.data.remote.datasource.FriendRemoteDataSource
import com.igluesmik.sopt.data.remote.datasource.UserRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class UserRepoImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val friendRemoteDataSource: FriendRemoteDataSource
) : UserRepo {
    override fun signIn(email: String, password: String): Single<ResponseSignIn> {
        return userRemoteDataSource.signIn(email, password)
    }

    override fun signUp(email: String, password: String, userName: String): Single<ResponseSignUp> {
        return userRemoteDataSource.signUp(email, password, userName)
    }

    override fun getUsers(): Single<List<Friend>> {
        return friendRemoteDataSource.getUsers()
            .subscribeOn(Schedulers.io())
            .map {
                val list= mutableListOf<Friend>()
                for(d in it.data){
                    list.add(Friend(d.firstName, d.lastName, d.avatar))
                }
                list.toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}