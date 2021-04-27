package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
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

            //todo make this into a function to stop code repetition
            btnSaveLoadLocal.isClickable = false
            btnSaveLoadRemote.isClickable=true
            btnSaveLoadLocal.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.black))
            btnSaveLoadRemote.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.purple_500))

            btnSaveLoadLocal.setOnClickListener{Resources.saveToLocalDb = true
                btnSaveLoadLocal.isClickable = false
                btnSaveLoadRemote.isClickable=true
                btnSaveLoadLocal.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.black))
                btnSaveLoadRemote.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.purple_500))
            }
            btnSaveLoadRemote.setOnClickListener{
                Resources.saveToLocalDb = false
                btnSaveLoadLocal.isClickable = true
                btnSaveLoadRemote.isClickable=false
                btnSaveLoadLocal.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.purple_500))
                btnSaveLoadRemote.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.black))
            }
        }
    }
}