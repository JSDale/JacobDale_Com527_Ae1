package com.example.week9.network.jacobdale_com527_ae1


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), LocationListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestLocation()

        val nv = findViewById<NavigationView>(R.id.nv)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val mainMapFragment = FragmentMainMap()
        val addPoiFragment = FragmentAddPOI()

        nv.setNavigationItemSelectedListener {
            try
            {
                val frag: Fragment = if (it.itemId == R.id.MainMapView) mainMapFragment else addPoiFragment
                drawerLayout.closeDrawers()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout1, frag).commit()

                true
            }
            catch(e: Exception)
            {
                e.printStackTrace()
                false
            }
        }
        //try to fill with main map fragment by default
        try
        {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout1, mainMapFragment).commit()
        }
        catch(e: Exception)
        {
            e.printStackTrace()
        }
    }

    fun requestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            val mgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, this)
        }
        else
        {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
    }

    override fun onLocationChanged(location: Location) {
        //this is handled in FragmentMainMap.kt
    }
}