package com.igluesmik.sopt.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igluesmik.sopt.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase(){
    abstract fun profileDao() : ProfileDao
}