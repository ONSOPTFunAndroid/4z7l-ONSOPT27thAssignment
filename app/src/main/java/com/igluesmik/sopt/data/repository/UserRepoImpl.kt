package com.igluesmik.sopt.data.repository

import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.data.model.domain.User
import com.igluesmik.sopt.data.model.mapper.FriendMapper
import com.igluesmik.sopt.data.model.network.response.ResponseSignIn
import com.igluesmik.sopt.data.model.network.response.ResponseSignUp
import com.igluesmik.sopt.data.remote.datasource.FriendRemoteDataSource
import com.igluesmik.sopt.data.remote.datasource.UserRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepoImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val friendRemoteDataSource: FriendRemoteDataSource
) : UserRepo {
    override fun signIn(email: String, password: String): Single<User> {
        return userRemoteDataSource.signIn(email, password)
            .subscribeOn(Schedulers.io())
            .map {
                User(it.data.email, it.data.userName)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun signUp(email: String, password: String, userName: String): Completable {
        return Completable.fromSingle(
            userRemoteDataSource.signUp(email, password, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun getUsers(): Single<List<Friend>> {
        return friendRemoteDataSource.getUsers()
            .subscribeOn(Schedulers.io())
            .map {
                FriendMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}