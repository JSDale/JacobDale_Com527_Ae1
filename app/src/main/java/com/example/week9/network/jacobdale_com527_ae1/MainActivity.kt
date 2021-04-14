package com.example.week9.network.jacobdale_com527_ae1


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.LocationManager
import android.location.LocationListener
import android.location.Location
import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

class MainActivity : AppCompatActivity(), LocationListener
{
    private lateinit var mapView: MapView
    private var longitude: Double = -1.7945
    private var latitude: Double = 51.0688

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        setContentView(R.layout.activity_main)

        val btnSetZoomLevel = findViewById<Button>(R.id.btnSetZoom)
        val editTextZoomLevel = findViewById<EditText>(R.id.editTextZoomLevel)

        btnSetZoomLevel.setOnClickListener{setZoomLevel(editTextZoomLevel)}

        var zoomLevel = 16
        this.mapView = findViewById(R.id.mainMap)
        this.mapView.controller.setZoom(zoomLevel)
        this.mapView.controller.setCenter(GeoPoint(this.latitude, this.longitude))

        this.requestLocation()

        this.updateCentreMarker(this.latitude, this.longitude)

        var buttonRefresh = findViewById<Button>(R.id.btnRefresh)
        buttonRefresh.setOnClickListener{centreView()}
    }

    private fun setZoomLevel(editText: EditText)
    {
        try
        {
            this.mapView.controller.setZoom(editText.text.toString().toInt())
        }
        catch(e: Exception)
        {
            Toast.makeText(this@MainActivity, "Enter Zoom Number", Toast.LENGTH_SHORT).show()
        }

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
        this.updateMap(location)
        Toast.makeText(this@MainActivity, "${Resources.latitude}, ${Resources.longitude}", Toast.LENGTH_SHORT).show()
    }

    private fun updateMap(location: Location)
    {
        location?.apply{
            var longitude = this.longitude
            var latitude = this.latitude
            MainActivity@this.longitude = longitude
            MainActivity@this.latitude = latitude
            updateLongitudeAndLatitude(longitude, latitude)
            updateCentreMarker(latitude, longitude)
        }
    }

    private fun updateLongitudeAndLatitude(longitude: Double, latitude: Double)
    {
        this.latitude = latitude
        this.longitude = longitude
        Resources.latitude = latitude
        Resources.longitude = longitude
    }

    private fun updateCentreMarker(latitude: Double, longitude: Double)
    {
        mapView.overlays.clear()

        var items = ItemizedIconOverlay(this, arrayListOf<OverlayItem>(), null)
        var centre = OverlayItem("You", "You are here", GeoPoint(latitude, longitude))
        items.addItem(centre)
        mapView.overlays.add(items)
    }

    private fun centreView()
    {
        this.mapView.controller.setCenter(GeoPoint(this.latitude, this.longitude))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}