package com.ubaya.a160420119_uts.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.ubaya.a160420119_uts.R
import com.ubaya.a160420119_uts.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    companion object{
        val user_id = "USERID"
        val user_username = "USERNAME"
        val user_password = "PASSWORD"
        val user_bod = "BOD"
        val user_phone = "PHONE"
        val user_photo = "PHOTO"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtUsername = view.findViewById<TextInputEditText>(R.id.txtUsername)
        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        val shared: SharedPreferences? = activity?.getSharedPreferences("UbayaKuliner",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = shared?.edit()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val checkid = shared?.getString(user_id, (-1).toString())
        if(checkid != "-1"){
            val action = LoginFragmentDirections.actionPlaceFragment()
            Navigation.findNavController(view).navigate(action)
        }
        btnLogin.setOnClickListener {
            if(txtUsername.text.toString() != "" && txtPassword.text.toString() != ""){
                viewModel.login(txtUsername.text.toString(), txtPassword.text.toString())
                viewModel.userLD.observe(viewLifecycleOwner){user->
                    if(user.username == "" && user.password == ""){
                        Toast.makeText(activity, "Sorry, Username or password is not valid", Toast.LENGTH_SHORT).show()
                    } else {
                        editor?.putString(user_id, user.id)
                        editor?.putString(user_username, user.username)
                        editor?.putString(user_password, user.password)
                        editor?.putString(user_bod, user.bod)
                        editor?.putString(user_phone, user.phone)
                        editor?.putString(user_photo, user.photo_user_url)
                        editor?.apply()
                        val action = LoginFragmentDirections.actionPlaceFragment()
                        Navigation.findNavController(it).navigate(action)
                        Log.d("Login", "masuk ke login ")
                    }
                }
            } else{
                Toast.makeText(activity, "Wrong username/password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}