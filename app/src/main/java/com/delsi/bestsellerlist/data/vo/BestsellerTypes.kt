package com.delsi.bestsellerlist.data.vo


import com.google.gson.annotations.SerializedName

data class BestsellerTypesResponse(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<BestsellerType>?,
    @SerializedName("status")
    val status: String?
)