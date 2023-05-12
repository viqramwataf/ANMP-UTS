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
import com.google.gson.reflect.TypeToken
import com.ubaya.a160420119_uts.model.Place

class PlaceDetailViewModel (Application: Application): AndroidViewModel(Application){
    val placeLD = MutableLiveData<Place>()
    val placeLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(placeId: String) {
        placeLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        //file json
        val url = "http://10.0.2.2/anmp/detailplace.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<Place>>(it, object :TypeToken<ArrayList<Place>>(){}.type)
                result.forEach { p->
                    if (placeId == p.id){
                        placeLD.value = p
                    }
                }
                Log.d("result", result.toString())
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
}