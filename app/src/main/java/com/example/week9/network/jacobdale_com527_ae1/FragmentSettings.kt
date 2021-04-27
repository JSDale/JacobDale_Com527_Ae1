package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentSettings : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        Resources.context = context

        view?.apply{
            val btnSaveLoadLocal = findViewById<Button>(R.id.buttondSaveAndLoadToFromLocalDb)
            val btnSaveLoadRemote = findViewById<Button>(R.id.buttondSaveAndLoadToFromRemoteDb)

            btnSaveLoadLocal.setOnClickListener{Resources.saveToLocalDb = true}
            btnSaveLoadRemote.setOnClickListener{Resources.saveToLocalDb = false}
        }
    }
}