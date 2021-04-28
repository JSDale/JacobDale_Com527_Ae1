package com.example.week9.network.jacobdale_com527_ae1


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.kittinunf.fuel.httpPost
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), LocationListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE ), 0)

        Resources.poiDb = PointOfInterestDatabase.getDatabase(application)
        Resources.context = this
        try{
            Resources.pointsOfInterestList = Resources.poiDb.poiDAO().getAllPOIs()
        }catch(e: Exception){
            Resources.pointsOfInterestList = arrayListOf<PointOfInterest>()
        }


        this.requestLocation()

        val nv = findViewById<NavigationView>(R.id.nv)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val mainMapFragment = FragmentMainMap()
        val addPoiFragment = FragmentAddPointOfInterest()
        val settingsFragment = FragmentSettings()

        nv.setNavigationItemSelectedListener {
            try
            {
                val frag = if (it.itemId == R.id.MainMapView)
                    mainMapFragment
                else if (it.itemId == R.id.AddNewPOI)
                    addPoiFragment
                else
                    settingsFragment

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
        var mainMapLocationHandler = MainMapLocationHandler()

        location?.apply{
            Resources.longitude = this.longitude
            Resources.latitude = this.latitude
        }
        if(Resources.isOnMainMap)
        {
            mainMapLocationHandler.updateMap(location)
        }
    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText (this, "Provider disabled", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText (this, "Provider enabled", Toast.LENGTH_SHORT).show()
    }
    // Deprecated at API level 29, but must still be included, otherwise your
    // app will crash on lower-API devices as their API will try and call it
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onDestroy()
    {
        super.onDestroy()
        saveToDb()
    }

    override fun onStop()
    {
        super.onStop()
        saveToDb()
    }

    private fun saveToDb()
    {
        if(Resources.saveToLocalDb)
        {
            Resources.pointsOfInterestList.forEach {
                Resources.poiDb.poiDAO().insert(it)
            }
        }
        else
        {
            val url = "http://10.0.2.2:3000/poi/create"
            Resources.pointsOfInterestList.forEach {
                val postData = listOf("name" to it.title, "type" to it.type, "description" to it.description, "lon" to Resources.longitude, "lat" to Resources.latitude)
                url.httpPost(postData)
            }

        }
    }
}