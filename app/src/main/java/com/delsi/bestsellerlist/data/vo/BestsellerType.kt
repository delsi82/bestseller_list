package com.delsi.bestsellerlist.data.vo


import com.google.gson.annotations.SerializedName

data class BestsellerType(
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,
    @SerializedName("newest_published_date")
    val newestPublishedDate: String?,
    @SerializedName("oldest_published_date")
    val oldestPublishedDate: String?,
    @SerializedName("updated")
    val updated: String?
) {
    override fun toString(): String {
        return displayName ?: ""
    }
}