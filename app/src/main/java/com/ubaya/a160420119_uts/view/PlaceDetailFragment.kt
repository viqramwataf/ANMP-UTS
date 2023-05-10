package com.ubaya.a160420119_uts.view

import android.os.Bundle
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
import com.ubaya.a160420119_uts.viewmodel.PlaceDetailViewModel

class PlaceDetailFragment : Fragment() {
    private lateinit var viewModel: PlaceDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var placeId = ""

        arguments?.let{
            placeId = PlaceDetailFragmentArgs.fromBundle(requireArguments()).placeId
        }
        viewModel = ViewModelProvider(this).get(PlaceDetailViewModel::class.java)
        viewModel.fetch(placeId)

        val txtPlaceName = view.findViewById<TextView>(R.id.txtPlace)
        val txtLocation = view.findViewById<TextView>(R.id.txtLocation)
        val txtFnB = view.findViewById<TextView>(R.id.txtFnBMenu)
        val txtPhone = view.findViewById<TextView>(R.id.txtTelp)
        val txtReview = view.findViewById<TextView>(R.id.txtReview)
        val imgPhotoUrl = view.findViewById<ImageView>(R.id.imgPlaceDetail)
        val progressBarDtlPlace = view.findViewById<ProgressBar>(R.id.progressBarDtlPlace)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        viewModel.placeLD.observe(viewLifecycleOwner) {place->
            txtPlaceName.text = place.name
            txtLocation.text = "Location: " + place.location
            txtFnB.text = place.fnb_menu
            txtPhone.text = "Phone: " + place.phone
            txtReview.text = place.review
            imgPhotoUrl.loadImage(place.photo_url, progressBarDtlPlace)

            btnBack.setOnClickListener {
                val action = PlaceDetailFragmentDirections.actionPlaceFragmentFromDetail()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}