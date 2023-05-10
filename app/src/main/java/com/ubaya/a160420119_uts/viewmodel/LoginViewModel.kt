package com.ubaya.a160420119_uts.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160420119_uts.model.User
import com.ubaya.a160420119_uts.view.LoginFragment
import com.ubaya.a160420119_uts.view.LoginFragmentDirections

class LoginViewModel (Application: Application): AndroidViewModel(Application) {
    val userLD = MutableLiveData<ArrayList<User>>() //LD = live data
    val loginStatus = MutableLiveData<Boolean>( false)
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/viqramwataf/UTS-JSON/user"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<User>>(it, object :TypeToken<ArrayList<User>>(){}.type)
                userLD.value = result
                Log.d("Error 2:",result.toString())
                for (user in result) {
                    if (user.username==username && user.password==password) {
                        loginStatus.value = true
                    }
                }
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}