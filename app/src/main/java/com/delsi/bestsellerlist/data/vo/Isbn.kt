package com.delsi.bestsellerlist.data.vo


import com.google.gson.annotations.SerializedName

data class Isbn(
    @SerializedName("isbn10")
    val isbn10: String?,
    @SerializedName("isbn13")
    val isbn13: String?
)