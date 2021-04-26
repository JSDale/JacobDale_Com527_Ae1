package com.example.week9.network.jacobdale_com527_ae1

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem
import java.security.AccessController.getContext

class FragmentMainMap : Fragment(), LocationListener
{
    private lateinit var mapView: MapView
    private lateinit var myContext: Context
    private lateinit var activity: AppCompatActivity

    fun FragmentManager(mainActivity: AppCompatActivity)
    {
        this.activity = mainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_main_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        view?.apply{
            myContext = requireContext()
            Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))

            val btnSetZoomLevel = findViewById<Button>(R.id.btnSetZoom)
            val editTextZoomLevel = findViewById<EditText>(R.id.editTextZoomLevel)

            btnSetZoomLevel.setOnClickListener{setZoomLevel(editTextZoomLevel)}

            var zoomLevel = 16
            mapView = findViewById<MapView>(R.id.mainMap)
            mapView.controller.setZoom(zoomLevel)
            mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
            //setMapView(mapView)

            updateCentreMarker()

            var buttonRefresh = findViewById<Button>(R.id.btnRefresh)
            buttonRefresh.setOnClickListener{centreView()}
        }

    }

    private fun setZoomLevel(editText: EditText)
    {
        try
        {
            view?.apply{
                mapView.controller.setZoom(editText.text.toString().toInt())
            }
        }
        catch(e: Exception)
        {
            //Toast.makeText(MainActivity@this, "Enter Zoom Number", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onLocationChanged(location: Location) {
        this.updateMap(location)
        //Toast.makeText(thisMainActivity@this, "${Resources.latitude}, ${Resources.longitude}", Toast.LENGTH_SHORT).show()
    }

    fun updateMap(location: Location)
    {
        location?.apply{
            Resources.longitude = this.longitude
            Resources.latitude = this.latitude
            updateCentreMarker()
        }
    }

    fun updateCentreMarker()
    {
        mapView.overlays.clear()

        var items = ItemizedIconOverlay(myContext, arrayListOf<OverlayItem>(), null)
        var centre = OverlayItem("You", "You are here", GeoPoint( Resources.latitude, Resources.longitude))
        items.addItem(centre)
        mapView.overlays.add(items)
    }

    fun centreView()
    {
        mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
    }
}