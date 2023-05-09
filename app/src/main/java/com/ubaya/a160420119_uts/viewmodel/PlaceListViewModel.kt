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

class PlaceListViewModel (Application: Application): AndroidViewModel(Application) {
    val placeLD = MutableLiveData<ArrayList<Place>>() //LD: Live Data
    val placeLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG ="volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(){
        loadingLD.value = true
        placeLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://noinheim.my.id/ubayalib_api/get_books.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Place>>() { }.type
                val result = Gson().fromJson<ArrayList<Place>>(it, sType)
                placeLD.value = result
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
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}