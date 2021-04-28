package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class FragmentMainMap : Fragment()
{
    private lateinit var mapView: MapView
    private lateinit var mainMapLocationHandler: MainMapLocationHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_main_map, container, false)
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

            val btnSetZoomLevel = findViewById<Button>(R.id.btnSetZoom)
            val editTextZoomLevel = findViewById<EditText>(R.id.editTextZoomLevel)

            btnSetZoomLevel.setOnClickListener{setZoomLevel(editTextZoomLevel)}

            var zoomLevel = 16
            mapView = findViewById<MapView>(R.id.mainMap)
            mapView.controller.setZoom(zoomLevel)
            mapView.controller.setCenter(GeoPoint(Resources.latitude, Resources.longitude))
            Resources.mapView = mapView

            mainMapLocationHandler = MainMapLocationHandler()

            mainMapLocationHandler.updateCentreMarker()

            var buttonRefresh = findViewById<Button>(R.id.btnRefresh)
            buttonRefresh.setOnClickListener{mainMapLocationHandler.centreView()}
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
            Toast.makeText(Resources.context, "Enter Zoom Number", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onResume(view: View?)
//    {
//        //mainMapLocationHandler.
//    }
}