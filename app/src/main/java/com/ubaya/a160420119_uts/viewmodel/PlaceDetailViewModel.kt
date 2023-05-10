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
import com.ubaya.a160420119_uts.model.User

class PlaceDetailViewModel (Application: Application): AndroidViewModel(Application){
//    val placeLD = MutableLiveData<ArrayList<Place>>()
    val placeLD = MutableLiveData<Place>()
    val placeLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val detailStatus = MutableLiveData<Boolean>( false)
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(placeId: String) {
        placeLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/detailplace.json"
//        val url = "http://10.0.2.2/anmp/place.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                val result = Gson().fromJson<ArrayList<Place>>(it, object :TypeToken<ArrayList<Place>>(){}.type)
                val result = Gson().fromJson<ArrayList<Place>>(it, object :TypeToken<ArrayList<Place>>(){}.type)
                result.forEach { p->
                    if (placeId == p.id){
                        placeLD.value = p
                    }
                }
                Log.d("result", result.toString())
//                for (place in result) {
//                    if (place.id == placeId) {
//                        detailStatus.value = true
//                    }
//                }
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