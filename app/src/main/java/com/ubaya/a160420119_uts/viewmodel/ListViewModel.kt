package com.ubaya.a160420119_uts.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160420119_uts.model.Place

class ListViewModel {
    //this is observer
    val placeLD = MutableLiveData<ArrayList<Place>>() //LD: live data
    val placeLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        placeLoadErrorLD.value = false
        loadingLD.value = true

//        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/viqramwataf/UTS-JSON/"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Place>>() { }.type
                val result = Gson().fromJson<List<Place>>(it, sType)
                placeLD.value = result as ArrayList<Place>?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                placeLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    
    override fun onCleared() {
//        super.onCleared()
        queue?.cancelAll(TAG)
    }
}