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
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.model.Place
import com.ubaya.a160420119_uts.util.loadImage

class PlaceAdapter (val placeList: ArrayList<Place>):RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    class PlaceViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.place_list_item, parent,false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val txtPlaceName =holder.view.findViewById<TextView>(R.id.txtPlace)
        val txtLocationPlace = holder.view.findViewById<TextView>(R.id.txtLocation)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        val imgPlace = holder.view.findViewById<ImageView>(R.id.imgPlace)
        val idPlace = placeList[position].id.toString()

        imgPlace.loadImage(placeList[position].photo_url, progressBar)
        txtPlaceName.text = placeList[position].name
        txtLocationPlace.text = placeList[position].location

        btnDetail.setOnClickListener {
            val action = PlaceFragmentDirections.actionPlaceDetail(idPlace)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateListPlace(newPlaceList: ArrayList<Place>) {
        placeList.clear()
        placeList.addAll(newPlaceList)
        notifyDataSetChanged()
    }
}