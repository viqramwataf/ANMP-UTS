package com.ubaya.a160420119_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.model.Favorite
import com.ubaya.a160420119_uts.util.loadImage

class FavoriteAdapter (val favoriteList: ArrayList<Favorite>): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorite_list_item, parent,false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val txtFavoriteName = holder.view.findViewById<TextView>(R.id.txtFavorite)
        val progressBarFavorite = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        val imgFavorite = holder.view.findViewById<ImageView>(R.id.imgFavorite)

        imgFavorite.loadImage(favoriteList[position].photo_url, progressBarFavorite)
        txtFavoriteName.text = favoriteList[position].name
    }

    fun updateListFavorite(newFavoriteList: ArrayList<Favorite>) {
        favoriteList.clear()
        favoriteList.addAll(newFavoriteList)
        notifyDataSetChanged()
    }
}