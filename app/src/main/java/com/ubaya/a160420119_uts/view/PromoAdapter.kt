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
import com.ubaya.a160420119_uts.model.Promo
import com.ubaya.a160420119_uts.util.loadImage

class PromoAdapter (val promoList: ArrayList<Promo>): RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {
    class PromoViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.promo_list_item, parent,false)
        return PromoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promoList.size
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val txtNamePromo = holder.view.findViewById<TextView>(R.id.txtNamePromo)
        val txtPlacePromo = holder.view.findViewById<TextView>(R.id.txtPlacePromo)
        val txtDisc = holder.view.findViewById<TextView>(R.id.txtDisc)
        val progressBarPromo = holder.view.findViewById<ProgressBar>(R.id.progressBar3)
        val imgPromo = holder.view.findViewById<ImageView>(R.id.imgPromo)

        imgPromo.loadImage(promoList[position].photo_url, progressBarPromo)
        txtNamePromo.text = promoList[position].name_disc
        txtPlacePromo.text = promoList[position].name
        txtDisc.text = promoList[position].disc
    }

    fun updateListPromo(newPromoList: ArrayList<Promo>) {
        promoList.clear()
        promoList.addAll(newPromoList)
        notifyDataSetChanged()
    }
}