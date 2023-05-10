package com.ubaya.a160420119_uts.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.util.loadImage
import com.ubaya.a160420119_uts.viewmodel.LoginViewModel

class ProfileFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaKuliner",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = shared?.edit()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val txtUsername = view.findViewById<EditText>(R.id.txtUsernameProfile)
        val txtPassword = view.findViewById<EditText>(R.id.txtPasswordProfile)
        val txtBod = view.findViewById<EditText>(R.id.txtBodProfile)
        val txtPhone = view.findViewById<EditText>(R.id.txtPhoneProfile)
        val imgPhotoProfile = view.findViewById<ImageView>(R.id.imgPhotoProfile)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        val getUsername = shared?.getString(LoginFragment.user_username, (-1).toString())
        val getPassword = shared?.getString(LoginFragment.user_password, (-1).toString())
        val getBod = shared?.getString(LoginFragment.user_bod, (-1).toString())
        val getPhone = shared?.getString(LoginFragment.user_phone, (-1).toString())
        val getPhoto = shared?.getString(LoginFragment.user_photo, (-1).toString())

        txtUsername.setText(getUsername)
        txtPassword.setText(getPassword)
        txtBod.setText(getBod)
        txtPhone.setText(getPhone)

        imgPhotoProfile.loadImage(getPhoto)

        btnLogout.setOnClickListener {
            editor?.remove("USERID")
            editor?.remove("USERNAME")
            editor?.remove("PASSWORD")
            editor?.remove("BOD")
            editor?.remove("PHONE")
            editor?.remove("PHOTO")
            editor?.apply()
            val action = ProfileFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}