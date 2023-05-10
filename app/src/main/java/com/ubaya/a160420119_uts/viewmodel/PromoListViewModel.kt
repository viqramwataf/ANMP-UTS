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
import com.ubaya.a160420119_uts.model.Promo

class PromoListViewModel (Application: Application): AndroidViewModel(Application) {
    val promosLD = MutableLiveData<ArrayList<Promo>>()
    val promoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG ="volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(){
        loadingLD.value = true
        promoLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/promo.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Promo>>() { }.type
                val result = Gson().fromJson<ArrayList<Promo>>(it, sType)
                promosLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                promoLoadErrorLD.value = false
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