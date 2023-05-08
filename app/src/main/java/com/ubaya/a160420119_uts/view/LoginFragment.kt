package com.ubaya.a160420119_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.ubaya.a160420119_uts.R
import kotlinx.android.synthetic.main.fragment_login.*
class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val user = "viqram"
            val pass = "viqram"
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()
            if (username == user && password == pass) {
                val action = LoginFragmentDirections.actionLoginFragmentToPlaceFragment(username)
                Navigation.findNavController(it).navigate(action)
            }
            else {

            }
        }
    }
}