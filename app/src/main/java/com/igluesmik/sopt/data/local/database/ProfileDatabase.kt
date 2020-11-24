package com.igluesmik.sopt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igluesmik.sopt.data.local.dao.ProfileDao
import com.igluesmik.sopt.data.model.entity.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase(){
    abstract fun profileDao() : ProfileDao
}