package com.ubaya.a160420119_uts.model

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("id")
    val id:String?,
    @SerializedName("place_name")
    val name:String?,
    @SerializedName("location")
    val location:String?,
    @SerializedName("fnb_menu")
    val fnb_menu:String?,
    @SerializedName("phone")
    val phone:String?,
    @SerializedName("review")
    val review:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)

data class Favorite(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("place_name")
    val name:String?,
    @SerializedName("user_id")
    val userId:Int?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)

data class Promo(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("place_name")
    val name:String?,
    @SerializedName("name_disc")
    val name_disc:String?,
    @SerializedName("disc")
    val disc:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)

data class History(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("place_name")
    val name:String?,
    @SerializedName("user_id")
    val userId:Int?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("date_time")
    val dateTime:String?,
    @SerializedName("status_history")
    val status:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)

data class User(
    val id:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("password")
    val password:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    @SerializedName("phone")
    val phone:String?,
    @SerializedName("photo_user_url")
    val photo_user_url:String?,
)