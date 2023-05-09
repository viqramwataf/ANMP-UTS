package com.ubaya.a160420119_uts.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.viewmodel.PlaceListViewModel

class PlaceFragment : Fragment() {
    private lateinit var viewModel: PlaceListViewModel
    private val placeListAdapter = PlaceAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaKuliner",
            AppCompatActivity.MODE_PRIVATE)
        val checkid = shared?.getString(LoginFragment.user_id, (-1).toString())
        val checkuname= shared?.getString(LoginFragment.user_username, "")
    }

    fun observeViewModel(){
        viewModel.placeLD.observe(viewLifecycleOwner, Observer {
            placeListAdapter.updatePlaceList(it)
        })

        viewModel.placeLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            } else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recViewPlace = view?.findViewById<RecyclerView>(R.id.recView)
            val progressLoadPlace = view?.findViewById<ProgressBar>(R.id.progressLoad)
            if(it == true) {
                recViewPlace?.visibility = View.GONE
                progressLoadPlace?.visibility = View.VISIBLE
            } else {
                recViewPlace?.visibility = View.VISIBLE
                progressLoadPlace?.visibility = View.GONE
            }
        })
    }
}