package com.ubaya.a160420119_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.model.Place
import com.ubaya.a160420119_uts.util.loadImage

class PlaceAdapter (val placeList: ArrayList<Place>):RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    class PlaceViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.place_list_item, parent,false)

        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val txtNamePlace = holder.view.findViewById<TextView>(R.id.txtPlace)
        val txtLocation = holder.view.findViewById<TextView>(R.id.txtLocation)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        val imgPlace = holder.view.findViewById<ImageView>(R.id.imgPlace)
        val idPlace = placeList[position].id.toString()
        imgPlace.loadImage(placeList[position].photo_url, progressBar)
        txtNamePlace.text = placeList[position].name
        txtLocation.text = placeList[position].location
        btnDetail.setOnClickListener {
            val action = PlaceFragmentDirections.actionPlaceDetail(idPlace)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updatePlaceList(newStudentList: ArrayList<Place>) {
        placeList.clear()
        placeList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}