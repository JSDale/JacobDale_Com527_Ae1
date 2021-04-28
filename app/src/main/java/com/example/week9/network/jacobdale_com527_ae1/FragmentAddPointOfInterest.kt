package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

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
                var enteredPoi = PointOfInterest(id, title, type, description, Resources.latitude, Resources.longitude)
                Resources.pointsOfInterestList.add(enteredPoi)

                if(!Resources.saveToLocalDb)
                {
                    saveToRemote()
                }
                else{
                    Resources.poiDb.poiDAO().insert(enteredPoi)
                }

                editTextTitle.setText("")
                editTextType.setText("")
                editTextDescription.setText("")
            }
        }
    }

    private fun saveToRemote()
    {
        val url = "http://10.0.2.2:3000/poi/create"
        Resources.pointsOfInterestList.forEach {
            val postData = listOf("name" to it.title, "type" to it.type, "description" to it.description, "lon" to it.longitude, "lat" to it.latitude)
            url.httpPost(postData).response{ request, response, result ->
                when(result){
                    is Result.Success ->{
                        Toast.makeText(Resources.context, "Uploaded To Web Server", Toast.LENGTH_LONG).show()
                    }
                    is Result.Failure -> {
                        Toast.makeText(Resources.context, result.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}