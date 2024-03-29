package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

class FragmentPoiListMap : Fragment()
{
    private lateinit var mapView: MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_poi_map, container, false)
    }

    override fun onResume()
    {
        super.onResume()
        var mainMapLocationHandler = MainMapLocationHandler()
        var items = ItemizedIconOverlay(context, arrayListOf<OverlayItem>(), null)
        items = mainMapLocationHandler.getPoiMarkersDb(items)
        mapView.overlays.add(items)
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

            var mainMapLocationHandler = MainMapLocationHandler()
            var items = ItemizedIconOverlay(context, arrayListOf<OverlayItem>(), null)
            items = mainMapLocationHandler.getPoiMarkersDb(items)
            mapView.overlays.add(items)
        }
    }

    fun locationSelected(index: Int)
    {
        val poi = Resources.pointsOfInterestList[index]
        val latitude = poi.latitude
        val longitude = poi.longitude
        mapView.controller.setCenter(GeoPoint(latitude, longitude))
    }
}