package com.example.week9.network.jacobdale_com527_ae1

import android.location.Location
import androidx.core.content.ContextCompat
import com.example.week9.network.jacobdale_com527_ae1.Resources.context
import org.osmdroid.util.GeoPoint
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
        items = getPoiMarkersDb(items)
        var centre = OverlayItem("You", "You are here", GeoPoint(Resources.latitude, Resources.longitude))
        items.addItem(centre)
        centre.setMarker(ContextCompat.getDrawable(context, R.drawable.user_marker))

        mapView.overlays.add(items)
        centreView()
    }

    fun centreView()
    {
        mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
    }

    fun getPoiMarkersDb(items: ItemizedIconOverlay<OverlayItem>) : ItemizedIconOverlay<OverlayItem>
    {
        Resources.pointsOfInterestList.forEach{
            var newMarker = OverlayItem(it.title, it.description, GeoPoint(it.latitude, it.longitude))
            items.addItem(newMarker)
        }
        return items
    }
}