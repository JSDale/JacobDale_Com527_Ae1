package com.example.week9.network.jacobdale_com527_ae1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        Resources.context = context

        view?.apply{
            val btnAddPOI = findViewById<Button>(R.id.AddNewPOI)
            val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
            val editTextType = findViewById<EditText>(R.id.editTextType)
            val editTextDescription = findViewById<EditText>(R.id.editTextDescription)

            btnAddPOI.setOnClickListener{
                lifecycleScope.launch{
                    var id: Long = 0
                    var title = editTextTitle.text.toString()
                    var type = editTextType.text.toString()
                    var description = editTextDescription.text.toString()

                    withContext(Dispatchers.IO) {
                        var enteredPoi = PointOfInterest(id, title, type, description)
                        Resources.poiDb.poiDAO().insert(enteredPoi)
                    }
                }
                editTextTitle.setText("")
                editTextType.setText("")
                editTextDescription.setText("")
            }
        }
    }
}