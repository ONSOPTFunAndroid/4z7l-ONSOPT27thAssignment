package com.igluesmik.sopt.data.local.profile

import androidx.lifecycle.LiveData
import androidx.room.*
import com.igluesmik.sopt.data.model.entity.Profile
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProfileDao {
    @Query("SELECT * FROM Profile WHERE id = :id")
    fun getProfileById(id : Int) : Single<Profile>

    @Query("SELECT * FROM Profile")
    fun getAll() : LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile : Profile) : Completable

    @Query("DELETE FROM Profile WHERE id = :id")
    fun delete(id : Int) : Completable

    @Query("DELETE FROM Profile")
    fun deleteAll() : Completable
}