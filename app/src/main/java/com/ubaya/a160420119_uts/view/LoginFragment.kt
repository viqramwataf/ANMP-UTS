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
        val user_id = "IDUSER"
        val user_username = "USERNAME"
        val user_password = "PASSWORD"
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
        var id = ""
        var username = ""
        var password = ""

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val checkid = shared?.getString(user_id, (-1).toString())
        if(checkid != "-1"){
            val action = LoginFragmentDirections.actionPlaceFragment()
            Navigation.findNavController(view).navigate(action)
        }
        btnLogin.setOnClickListener {
            if(txtUsername.text.toString() != "" && txtPassword.text.toString() != ""){
                viewModel.login(txtUsername.text.toString(), txtPassword.text.toString())
                viewModel.loginStatus.observe(viewLifecycleOwner){user->
                    Log.d("Error 1:",user.toString())
                    if(!user){
                        Toast.makeText(activity, "Sorry, Username or password is not valid", Toast.LENGTH_SHORT).show()
                    } else {
                        editor?.putString(user_id, id)
                        editor?.putString(user_username, username)
                        editor?.putString(user_password, password)
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