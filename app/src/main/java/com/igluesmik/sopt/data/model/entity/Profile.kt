package com.igluesmik.sopt.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class Profile (
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    var title : String,
    var subtitle : String,
    var resourceId : Int,
    var isAddress : Boolean
)