package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.responseJson

class FragmentSettings : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        Resources.context = context

        view?.apply{
            val btnSaveLoadLocal = findViewById<Button>(R.id.buttondSaveAndLoadToFromLocalDb)
            val btnSaveLoadRemote = findViewById<Button>(R.id.buttondSaveAndLoadToFromRemoteDb)

            savedToLocalIsSelected(btnSaveLoadLocal, btnSaveLoadRemote)

            btnSaveLoadLocal.setOnClickListener{Resources.saveToLocalDb = true
                savedToLocalIsSelected(btnSaveLoadLocal, btnSaveLoadRemote)
                var myActivity = activity as MainActivity
                Resources.poiDb =  PointOfInterestDatabase.getDatabase(myActivity.application)
            }
            btnSaveLoadRemote.setOnClickListener{
                Resources.saveToLocalDb = false
                btnSaveLoadLocal.isClickable = true
                btnSaveLoadRemote.isClickable=false
                btnSaveLoadLocal.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.purple_500))
                btnSaveLoadRemote.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.black))
                loadFromWebServer()
            }
        }
    }

    private fun savedToLocalIsSelected(btnSaveLoadLocal: Button, btnSaveLoadRemote: Button)
    {
        btnSaveLoadLocal.isClickable = false
        btnSaveLoadRemote.isClickable=true
        btnSaveLoadLocal.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.black))
        btnSaveLoadRemote.setBackgroundColor(ContextCompat.getColor(Resources.context,R.color.purple_500))
    }

    private fun loadFromWebServer()
    {
        val url = "http://10.0.2.2:3000/poi/all"
        url.httpGet().responseJson{request, response, result ->
            when(result){
                is Result.Success ->{
                    val jsonArray = result.get().array()
                    for(i in 0 until jsonArray.length()){
                        val currentObj = jsonArray.getJSONObject(i)
                        try{
                            val id = currentObj.getString("id")
                            val title = currentObj.getString("name")
                            val type = currentObj.getString("type")
                            val description = currentObj.getString("description")
                            val lat = currentObj.getString("lat").toDouble()
                            val lon = currentObj.getString("lon").toDouble()
                            val poi = PointOfInterest(id.toLong(), title, type, description, lat, lon)
                            if(!Resources.pointsOfInterestList.contains(poi))
                            {
                                Resources.pointsOfInterestList.add(poi)
                            }
                        }catch(e:Exception)
                        {
                            Toast.makeText(Resources.context, e.message, Toast.LENGTH_SHORT)
                        }


                    }
                }
            }
        }
    }
}