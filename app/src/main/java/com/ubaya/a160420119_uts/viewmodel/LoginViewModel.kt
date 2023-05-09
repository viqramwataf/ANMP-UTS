package com.ubaya.a160420119_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.a160420119_uts.model.User

class LoginViewModel (Application: Application): AndroidViewModel(Application) {
    val userLD = MutableLiveData<User>() //LD = live data
    val statusLD = MutableLiveData<String>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/user.json"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                val result = Gson().fromJson<User>(it, User::class.java)
                userLD.value = result
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map.set("username", username)
                map.set("password", password)
                return map
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}