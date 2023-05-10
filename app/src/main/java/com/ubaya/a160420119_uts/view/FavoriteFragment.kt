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
import com.ubaya.a160420119_uts.viewmodel.FavoriteListViewModel
import com.ubaya.a160420119_uts.viewmodel.PlaceListViewModel

class FavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteListViewModel
    private val favoriteListAdapter = FavoriteAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    fun observeViewModel(){
        viewModel.favoritesLD.observe(viewLifecycleOwner, Observer {
            favoriteListAdapter.updateListFavorite(it)
        })

        viewModel.favoriteLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtErrorFavorite)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewFavorite = view?.findViewById<RecyclerView>(R.id.recViewFavorite)
            val progressLoadFavorite = view?.findViewById<ProgressBar>(R.id.progressLoadFavorite)
            if(it == true) {
                recViewFavorite?.visibility = View.GONE
                progressLoadFavorite?.visibility = View.VISIBLE
            } else {
                recViewFavorite?.visibility = View.VISIBLE
                progressLoadFavorite?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteListViewModel::class.java)
        viewModel.refresh()
        val recView = view.findViewById<RecyclerView>(R.id.recViewFavorite)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutFavorite)
        val txtError = view.findViewById<TextView>(R.id.txtErrorFavorite)
        val progressLoadFavorite = view.findViewById<ProgressBar>(R.id.progressLoadFavorite)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = favoriteListAdapter
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