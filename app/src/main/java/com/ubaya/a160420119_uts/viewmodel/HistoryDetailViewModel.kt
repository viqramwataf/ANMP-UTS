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
import com.ubaya.a160420119_uts.model.History
import com.ubaya.a160420119_uts.model.Place

class HistoryDetailViewModel (Application: Application): AndroidViewModel(Application){
    val historyLD = MutableLiveData<History>()
    val historyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(historyId: String) {
        historyLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/detailhistory.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<History>>(it, object :
                    TypeToken<ArrayList<History>>(){}.type)
                result.forEach { p->
                    if (historyId == p.id){
                        historyLD.value = p
                    }
                }
                Log.d("result", result.toString())
                loadingLD.value = false
                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                historyLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}