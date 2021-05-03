package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PoiDescriptionActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_information)

        val mapFragment = FragmentPoiDescriptionMap()
        //val recyclerFragment = FragmentPoiDescriptionRecyclcer()

        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMap, mapFragment).commit()
        //supportFragmentManager.beginTransaction().replace(R.id.frameLayoutRecycler, recyclerFragment).commit()
    }
}