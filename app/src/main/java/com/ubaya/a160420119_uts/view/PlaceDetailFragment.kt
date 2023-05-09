package com.ubaya.a160420119_uts.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160420119_uts.R
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
        val txtPhone = view.findViewById<TextView>(R.id.txtPhone)
        val imgPhotoUrl = view.findViewById<ImageView>(R.id.imgDetailBuku)
        val progressBarDtlPlace = view.findViewById<ProgressBar>(R.id.progressBarDtlPlace)
        btnReview
        val txtReview = view.findViewById<TextView>(R.id.TextView2)

        viewModel.placeLD.observe(viewLifecycleOwner) {book->
            txtJudulDetail.text = book.bookTitle
            txtDetailPengarang.text = "Pengarang ${book.penulis}"
            txtPenerbit.text = "Penerbit: ${book.penerbit}, ${book.tahunTerbit}"
            txtDimensi.text = "Dimensi: ${book.dimensi}"
            txtIsbn.text = "ISBN: ${book.isbn}"
            txtKetersediaan.text = "Ketersediaan: ${book.ketersediaan}"
            if(book.ketersediaan == "Tersedia"){
                txtKetersediaan.setTextColor(Color.parseColor("#59FB03"))
            } else{
                txtKetersediaan.setTextColor(Color.parseColor("#FB0303"))
            }
            txtSinopsis.text = book.sinopsis
            imgDetailBuku.loadImage(book.gambar, progressBar3)
        }
    }

}