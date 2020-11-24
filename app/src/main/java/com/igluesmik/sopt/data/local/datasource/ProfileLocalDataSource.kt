package com.igluesmik.sopt.data.local.datasource

import androidx.lifecycle.LiveData
import com.igluesmik.sopt.data.model.entity.Profile
import io.reactivex.Completable
import io.reactivex.Single

interface ProfileLocalDataSource {
    fun getProfileById(id: Int): Single<Profile>
    fun getAll(): LiveData<List<Profile>>
    fun insert(profile: Profile): Completable
    fun delete(id: Int): Completable
    fun deleteAll(): Completable
}