package com.igluesmik.sopt.data.local.datasource

import androidx.lifecycle.LiveData
import com.igluesmik.sopt.data.local.dao.ProfileDao
import com.igluesmik.sopt.data.model.entity.Profile
import io.reactivex.Completable
import io.reactivex.Single

class ProfileLocalDataSourceImpl(private val profileDao: ProfileDao) : ProfileLocalDataSource {
    override fun getProfileById(id: Int): Single<Profile> {
        return profileDao.getProfileById(id)
    }

    override fun getAll(): LiveData<List<Profile>> {
        return profileDao.getAll()
    }

    override fun insert(profile: Profile): Completable {
        return profileDao.insert(profile)
    }

    override fun delete(id: Int): Completable {
        return profileDao.delete(id)
    }

    override fun deleteAll(): Completable {
        return profileDao.deleteAll()
    }

}