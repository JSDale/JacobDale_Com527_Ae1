package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class FragmentPoiDescriptionMap : Fragment()
{
    private lateinit var mapView: MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_poi_map, container, false)
    }

    override fun onResume()
    {
        super.onResume()
        Resources.context = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        Resources.context = context

        view?.apply{
            Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
            var zoomLevel = 16
            mapView = findViewById<MapView>(R.id.poiMap)
            mapView.controller.setZoom(zoomLevel)
            mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
        }
    }
}