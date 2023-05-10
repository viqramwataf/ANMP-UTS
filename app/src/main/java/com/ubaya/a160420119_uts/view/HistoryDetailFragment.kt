package com.ubaya.a160420119_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.util.loadImage
import com.ubaya.a160420119_uts.viewmodel.HistoryDetailViewModel

class HistoryDetailFragment : Fragment() {
    private lateinit var viewModel: HistoryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var historyId = ""

        arguments?.let{
            historyId = HistoryDetailFragmentArgs.fromBundle(requireArguments()).historyId
        }
        viewModel = ViewModelProvider(this).get(HistoryDetailViewModel::class.java)
        viewModel.fetch(historyId)

        val txtHistoryDetailPlaceName = view.findViewById<TextView>(R.id.txtHistoryDetailPlaceName)
        val txtHistoryDetailDateTime = view.findViewById<TextView>(R.id.txtHistoryDetailDateTime)
        val txtHistoryDetailStatus = view.findViewById<TextView>(R.id.txtHistoryDetailStatus)
        val txtHistoryDetailOrder = view.findViewById<TextView>(R.id.txtHistoryDetailOrder)
        val txtHistoryDetailTotPrice = view.findViewById<TextView>(R.id.txtHistoryDetailTotPrice)
        val txtHistoryDetailQuantity = view.findViewById<TextView>(R.id.txtHistoryDetailQuantity)
        val txtHistoryDetailRate = view.findViewById<TextView>(R.id.txtHistoryDetailRate)
        val imgHistoryDetail = view.findViewById<ImageView>(R.id.imgHistoryDetail)
        val progressBarDtlHistory = view.findViewById<ProgressBar>(R.id.progressBarDtlHistory)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        viewModel.historyLD.observe(viewLifecycleOwner) {history->
            txtHistoryDetailPlaceName?.text = history.name
            txtHistoryDetailDateTime?.text = history.dateTime
            txtHistoryDetailStatus?.text = history.status
            txtHistoryDetailOrder?.text = history.order
            txtHistoryDetailTotPrice?.text = history.total_price
            txtHistoryDetailQuantity?.text = history.quantity
            txtHistoryDetailRate?.text = history.rate
            imgHistoryDetail.loadImage(history.photo_url, progressBarDtlHistory!!)

            btnBack.setOnClickListener {
                val action = HistoryFragmentDirections.actionHistoryDetail(historyId)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}