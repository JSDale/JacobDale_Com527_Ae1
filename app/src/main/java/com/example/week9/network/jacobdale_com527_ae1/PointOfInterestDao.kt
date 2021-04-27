package com.example.week9.network.jacobdale_com527_ae1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PointOfInterestDao
{
    @Query("SELECT * FROM pointsOfInterest")
    fun getAllPOIs(): List<PointOfInterest>

    @Insert
    fun insert(poi: PointOfInterest) : Long
}