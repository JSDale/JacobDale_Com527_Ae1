package com.example.week9.network.jacobdale_com527_ae1

import android.content.Context
import android.location.Location
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

class MainMapLocationHandler()
{
    private val mapView = Resources.mapView
    private val myContext = Resources.context

    fun updateMap(location: Location)
    {
        location?.apply{
            updateCentreMarker()
        }
    }

    fun updateCentreMarker()
    {
        mapView.overlays.clear()

        var items = ItemizedIconOverlay(myContext, arrayListOf<OverlayItem>(), null)
        var centre = OverlayItem("You", "You are here", GeoPoint(Resources.latitude, Resources.longitude))
        items.addItem(centre)
        mapView.overlays.add(items)
        centreView()
    }

    fun centreView()
    {
        mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
    }
}