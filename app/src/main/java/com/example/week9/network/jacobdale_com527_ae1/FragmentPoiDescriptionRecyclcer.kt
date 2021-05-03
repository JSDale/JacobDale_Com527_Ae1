package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentPoiDescriptionRecyclcer : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_poi_info_recycler, container, false)
    }
}