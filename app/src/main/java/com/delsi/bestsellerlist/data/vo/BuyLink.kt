package com.delsi.bestsellerlist.data.vo


import com.google.gson.annotations.SerializedName

data class BuyLink(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)