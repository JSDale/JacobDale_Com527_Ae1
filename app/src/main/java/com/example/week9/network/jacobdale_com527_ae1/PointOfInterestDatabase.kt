package com.example.week9.network.jacobdale_com527_ae1


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PointOfInterest::class), version = 1, exportSchema = false)
public abstract class PointOfInterestDatabase : RoomDatabase()
{
    abstract fun poiDAO(): PointOfInterestDao

    companion object{
        private var instance: PointOfInterestDatabase? = null

        fun getDatabase(ctx: Context) : PointOfInterestDatabase {
            var tmpInstance = instance
            if(tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    PointOfInterestDatabase::class.java,
                    "PointsOfInterestDatabase"
                ).build()
                instance = tmpInstance
            }
            return tmpInstance
        }
    }
}