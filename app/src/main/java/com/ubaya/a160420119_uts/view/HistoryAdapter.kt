package com.ubaya.a160420119_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.model.History
import com.ubaya.a160420119_uts.util.loadImage

class HistoryAdapter (val historyList: ArrayList<History>): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.history_list_item, parent,false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val txtHistoryPlaceName = holder.view.findViewById<TextView>(R.id.txtHistoryPlace)
        val txtDateTime = holder.view.findViewById<TextView>(R.id.txtDate)
        val txtStatus = holder.view.findViewById<TextView>(R.id.txtStatus)

        val progressBarHistory = holder.view.findViewById<ProgressBar>(R.id.progressBar4)
        val imgHistory = holder.view.findViewById<ImageView>(R.id.imgHistory)

        imgHistory.loadImage(historyList[position].photo_url, progressBarHistory)
        txtHistoryPlaceName.text = historyList[position].name
        txtDateTime.text = historyList[position].dateTime
        txtStatus.text = historyList[position].status
    }

    fun updateListHistory(newHistoryList: ArrayList<History>) {
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}