package com.ubaya.a160420119_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var viewModel: ListViewModel
    private val placeAdapter = PlaceAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_place)
//        drawerLayout = findViewById(R.id.drawerLayout)
//        navController =
//            (supportFragmentManager.findFragmentById(R.id.itemList) as
//                    NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        bottomNav.setupWithNavController(navView, navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlaceAdapter
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }
}