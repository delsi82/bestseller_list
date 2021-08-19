package com.delsi.bestsellerlist.data.vo


import com.google.gson.annotations.SerializedName

data class BooksList(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("last_modified")
    val lastModified: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: ResultsByType?,
    @SerializedName("status")
    val status: String?
)