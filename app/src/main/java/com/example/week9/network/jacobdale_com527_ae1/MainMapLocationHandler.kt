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
        var centre2 = OverlayItem("You", "You are here", GeoPoint(-1.4042, 50.9035))
        items.addItem(centre)
        if(!Resources.saveToLocalDb)
        {
            items = getPoiMarkersRemoteDb(items)
        }
        mapView.overlays.add(items)
        centreView()
    }

    fun centreView()
    {
        mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
    }

    fun getPoiMarkersRemoteDb(items: ItemizedIconOverlay<OverlayItem>) : ItemizedIconOverlay<OverlayItem>
    {
        Resources.pointsOfInterestList.forEach{
            var newMarker = OverlayItem(it.title, it.description, GeoPoint(it.latitude, it.longitude))
            items.addItem(newMarker)
        }
        return items
    }
}