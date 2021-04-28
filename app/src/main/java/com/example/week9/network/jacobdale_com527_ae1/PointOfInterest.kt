package com.example.week9.network.jacobdale_com527_ae1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pointsOfInterest")
data class PointOfInterest(@PrimaryKey(autoGenerate = true) val id: Long, val title: String, val type: String, val description: String,
                           val longitude: Double, val latitude: Double)
{
    
}