package com.example.week9.network.jacobdale_com527_ae1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pointsOfInterest")
/**
 * @param id the id of the POI
 * @param title the title of the POI
 * @param type the type of the POI
 * @param description the description of the POI
 * @param longitude the longitude of the POI
 * @param latitude the latitude of the POI
 */
data class PointOfInterest(@PrimaryKey(autoGenerate = true) val id: Long, val title: String, val type: String, val description: String,
                           val longitude: Double, val latitude: Double)
{
    
}