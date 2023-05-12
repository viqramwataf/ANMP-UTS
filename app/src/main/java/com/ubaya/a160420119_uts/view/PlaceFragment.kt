package com.ubaya.a160420119_uts.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
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
import com.ubaya.a160420119_uts.viewmodel.PlaceListViewModel

class PlaceFragment : Fragment() {
    private lateinit var viewModel: PlaceListViewModel
    private val placeListAdapter = PlaceAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    fun observeViewModel(){
        viewModel.placesLD.observe(viewLifecycleOwner, Observer {
            placeListAdapter.updateListPlace(it)
        })

        viewModel.placeLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtErrorPlace)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        //recycle view
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewPlace = view?.findViewById<RecyclerView>(R.id.recViewPlace)
            val progressLoadPlace = view?.findViewById<ProgressBar>(R.id.progressLoadPlace)
            if(it == true) {
                recViewPlace?.visibility = View.GONE
                progressLoadPlace?.visibility = View.VISIBLE
            } else {
                recViewPlace?.visibility = View.VISIBLE
                progressLoadPlace?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //list view model
        viewModel = ViewModelProvider(this).get(PlaceListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewPlace)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutPlace)
        val txtError = view.findViewById<TextView>(R.id.txtErrorPlace)
        val progressLoadPlace = view.findViewById<ProgressBar>(R.id.progressLoadPlace)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = placeListAdapter
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoadPlace.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
}