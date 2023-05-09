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

data class Menu(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("fnb_menu_id")
    val fnbmenuId:Int?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("price")
    val price:Double?,
    @SerializedName("photo_url")
    val photo_url:Double?,
)

data class Review(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("user_id")
    val userId:Int?,
    @SerializedName("comment")
    val comment:String?,
)

data class Favorite(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("user_id")
    val userId:Int?,
)

data class Promo(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("disc")
    val disc:Int?,
)

data class History(
    val id:String?,
    @SerializedName("place_id")
    val placeId:Int?,
    @SerializedName("user_id")
    val userId:Int?,
    @SerializedName("status")
    val status:String?,
)

data class Profile(
    val id:String?,
    @SerializedName("username")
    val username:String?,
    @SerializedName("password")
    val password:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    @SerializedName("phone")
    val phone:String?,
    @SerializedName("photo_url")
    val photo_url:String?,
)