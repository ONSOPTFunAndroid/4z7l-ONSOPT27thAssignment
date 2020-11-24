package com.igluesmik.sopt.data.repository

import androidx.lifecycle.LiveData
import com.igluesmik.sopt.data.local.datasource.ProfileLocalDataSource
import com.igluesmik.sopt.data.model.entity.Profile
import io.reactivex.Completable
import io.reactivex.Single

class ProfileRepoImpl(private val profileLocalDataSource: ProfileLocalDataSource) : ProfileRepo {
    override fun getProfileById(id: Int): Single<Profile> {
        return profileLocalDataSource.getProfileById(id)
    }

    override fun getAll(): LiveData<List<Profile>> {
        return profileLocalDataSource.getAll()
    }

    override fun insert(profile: Profile): Completable {
        return profileLocalDataSource.insert(profile)
    }

    override fun delete(id: Int): Completable {
        return profileLocalDataSource.delete(id)
    }

    override fun deleteAll(): Completable {
        return profileLocalDataSource.deleteAll()
    }
}