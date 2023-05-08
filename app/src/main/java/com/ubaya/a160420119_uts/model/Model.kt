package com.ubaya.a160420119_uts.model

import com.google.gson.annotations.SerializedName

data class User(
    val id:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("password")
    val password:String?,
)

data class Place(
    val id:String?,
    @SerializedName("place_name")
    val name:String?,
    @SerializedName("location")
    val location:String?,
    @SerializedName("fnb_menu")
    val fnb_menu:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)