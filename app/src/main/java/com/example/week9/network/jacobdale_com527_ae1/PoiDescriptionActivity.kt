package com.example.week9.network.jacobdale_com527_ae1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class PoiDescriptionActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi_information)

        val mapFragment = FragmentPoiListMap()
        val recyclerFragment = FragmentPoiDescriptionRecyclcer()

        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMap, mapFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutRecycler, recyclerFragment).commit()

        recyclerFragment.callback = {mapFragment.locationSelected(it)}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.MainMenu ->
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }
}