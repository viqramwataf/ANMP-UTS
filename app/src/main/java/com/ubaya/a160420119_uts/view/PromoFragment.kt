package com.ubaya.a160420119_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.viewmodel.PromoListViewModel

class PromoFragment : Fragment() {
    private lateinit var viewModel: PromoListViewModel
    private val promoListAdapter = PromoAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }

    fun observeViewModel(){
        viewModel.promosLD.observe(viewLifecycleOwner, Observer {
            promoListAdapter.updateListPromo(it)
        })

        viewModel.promoLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtErrorPromo)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewPromo = view?.findViewById<RecyclerView>(R.id.recViewPromo)
            val progressLoadPromo = view?.findViewById<ProgressBar>(R.id.progressLoadPromo)
            if(it == true) {
                recViewPromo?.visibility = View.GONE
                progressLoadPromo?.visibility = View.VISIBLE
            } else {
                recViewPromo?.visibility = View.VISIBLE
                progressLoadPromo?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PromoListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewPromo)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutPromo)
        val txtError = view.findViewById<TextView>(R.id.txtErrorPromo)
        val progressLoadPromo = view.findViewById<ProgressBar>(R.id.progressLoadPromo)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = promoListAdapter
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoadPromo.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
}