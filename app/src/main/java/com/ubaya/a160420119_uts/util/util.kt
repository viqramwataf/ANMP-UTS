package com.ubaya.a160420119_uts.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url: String?, progressBar: ProgressBar?= null){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(com.ubaya.a160420119_uts.R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess(){
                progressBar?.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }

        })
}
