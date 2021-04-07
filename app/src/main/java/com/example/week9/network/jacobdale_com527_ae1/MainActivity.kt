package com.example.week9.network.jacobdale_com527_ae1


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

class MainActivity : AppCompatActivity(), LocationListener
{
    private lateinit var editTextZoomLevel: EditText
    private lateinit var mapView: MapView
    private var longitude: Double = -1.7945
    private var latitude: Double = 51.0688

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_main)

        this.editTextZoomLevel = findViewById(R.id.editTextZoomLevel)

        //editTextZoomLevel.setOnClickListener(setZoomLevel(editTextZoomLevel))

        var zoomLevel = 16
        this.mapView = findViewById(R.id.mainMap)
        this.mapView.controller.setZoom(zoomLevel)
        this.mapView.controller.setCenter(GeoPoint(this.latitude, this.longitude))

        this.requestLocation()
    }

    private fun setZoomLevel(editText: EditText)
    {
        this.mapView.controller.setZoom(editText.text.toString().toInt())
    }

    private fun requestLocation() {

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

    override fun onLocationChanged(location: Location)
    {
        //TODO("Not yet implemented")
        Toast.makeText(this@MainActivity, "update", Toast.LENGTH_SHORT).show()
        this.updateMap(location)
    }

    private fun updateMap(location: Location)
    {
        location?.apply{
            var longitude = this.longitude
            var latitude = this.latitude
            this@MainActivity.mapView.controller.setCenter(GeoPoint(latitude, longitude))
        }
    }
}