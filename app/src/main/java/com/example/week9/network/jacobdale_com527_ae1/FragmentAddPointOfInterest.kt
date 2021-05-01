package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAddPointOfInterest : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    {
        return inflater?.inflate(R.layout.fragment_add_new_poi, container, false)
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
            val btnAddPOI = findViewById<Button>(R.id.buttonAddPOI)
            val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
            val editTextType = findViewById<EditText>(R.id.editTextType)
            val editTextDescription = findViewById<EditText>(R.id.editTextDescription)

            btnAddPOI.setOnClickListener{

                var id: Long = 0
                var title = editTextTitle.text.toString()
                var type = editTextType.text.toString()
                var description = editTextDescription.text.toString()
                var enteredPoi = PointOfInterest(id, title, type, description, Resources.longitude, Resources.latitude)
                Resources.pointsOfInterestList.add(enteredPoi)

                if(!Resources.saveToLocalDb)
                {
                    saveToRemote()
                }
                else{
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO){
                            Resources.poiDb.poiDAO().insert(enteredPoi)
                        }
                    }
                }

                Toast.makeText(Resources.context, "Added", LENGTH_SHORT)

                editTextTitle.setText("")
                editTextType.setText("")
                editTextDescription.setText("")
            }
        }
    }

    private fun saveToRemote()
    {
        val url = "http://10.0.2.2:3000/poi/create"
        Resources.pointsOfInterestList.forEach{
            val postData = listOf("name" to it.title, "type" to it.type, "description" to it.description, "lon" to it.longitude, "lat" to it.latitude)
            url.httpPost(postData)
        }
    }
}