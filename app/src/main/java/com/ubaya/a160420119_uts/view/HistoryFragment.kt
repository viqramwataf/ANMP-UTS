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
import com.ubaya.a160420119_uts.viewmodel.HistoryListViewModel

class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryListViewModel
    private val historyListAdapter = HistoryAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    fun observeViewModel(){
        viewModel.historiesLD.observe(viewLifecycleOwner, Observer {
            historyListAdapter.updateListHistory(it)
        })

        viewModel.historyLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtErrorHistory)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewHistory = view?.findViewById<RecyclerView>(R.id.recViewHistory)
            val progressLoadHistory = view?.findViewById<ProgressBar>(R.id.progressLoadHistory)
            if(it == true) {
                recViewHistory?.visibility = View.GONE
                progressLoadHistory?.visibility = View.VISIBLE
            } else {
                recViewHistory?.visibility = View.VISIBLE
                progressLoadHistory?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewHistory)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutHistory)
        val txtError = view.findViewById<TextView>(R.id.txtErrorHistory)
        val progressLoadFavorite = view.findViewById<ProgressBar>(R.id.progressLoadHistory)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = historyListAdapter
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoadFavorite.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
}