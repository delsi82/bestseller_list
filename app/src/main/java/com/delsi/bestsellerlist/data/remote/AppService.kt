package com.delsi.bestsellerlist.data.remote

import com.delsi.bestsellerlist.data.AppConst
import com.delsi.bestsellerlist.data.vo.BestsellerTypesResponse
import com.delsi.bestsellerlist.data.vo.BooksList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {


    @GET("${AppConst.API_BASE}/lists/names.json")
    suspend fun getTypes(@Query("api-key") apiKey: String): Response<BestsellerTypesResponse>

    @GET("${AppConst.API_BASE}/lists/current/{list}.json")
    suspend fun getBooks(
        @Path("list") type: String,
        @Query("api-key") apiKey: String
    ): Response<BooksList>
}