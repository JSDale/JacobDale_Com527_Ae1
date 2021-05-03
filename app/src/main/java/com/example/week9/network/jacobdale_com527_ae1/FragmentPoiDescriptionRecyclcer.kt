package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.osmdroid.config.Configuration

class FragmentPoiDescriptionRecyclcer : Fragment()
{
    lateinit var global_poiListView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_poi_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        view.apply {
            Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
            val poiListView = findViewById<RecyclerView>(R.id.pointOfInterestListView)
            global_poiListView = poiListView
            poiListView.layoutManager = LinearLayoutManager(context)
            var titles = arrayListOf<String>()
            var types = arrayListOf<String>()

            Resources.pointsOfInterestList.forEach{
                titles.add(it.title)
                types.add(it.type)
            }

            poiListView.adapter = MyAdapter(titles, types)
        }

    }

    override fun onResume() {
        super.onResume()
        view.apply {
            Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
            global_poiListView.layoutManager = LinearLayoutManager(context)
            var titles = arrayListOf<String>()
            var types = arrayListOf<String>()

            Resources.pointsOfInterestList.forEach{
                titles.add(it.title)
                types.add(it.type)
            }

            global_poiListView.adapter = MyAdapter(titles, types)
        }
    }
}