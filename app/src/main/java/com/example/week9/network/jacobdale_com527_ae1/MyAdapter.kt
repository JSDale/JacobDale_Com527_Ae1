package com.example.week9.network.jacobdale_com527_ae1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val titles: List<String>, val descriptions: List<String>,
val callback: ((Int) -> Unit)?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tvName = itemView.findViewById(R.id.pointOfInterestTitle) as TextView
        val tvDescription = itemView.findViewById(R.id.pointOfInterestType) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) : RecyclerView.ViewHolder
    {
        // Inflate the XML layout and create a ViewHolder using it

        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedLayout = layoutInflater.inflate(R.layout.list_item_layout, parent, false)
        return MyViewHolder(inflatedLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int)
    {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.tvName.text = titles[index]
        myViewHolder.tvDescription.text = descriptions[index]

        holder.itemView.setOnClickListener{ callback?.invoke(index) }
    }

    override fun getItemCount(): Int
    {
        return titles.size
    }
}