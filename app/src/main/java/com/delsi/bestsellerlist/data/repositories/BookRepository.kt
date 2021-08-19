package com.delsi.bestsellerlist.data.repositories

import com.delsi.bestsellerlist.data.AppConst
import com.delsi.bestsellerlist.data.Resource
import com.delsi.bestsellerlist.data.remote.AppService
import com.delsi.bestsellerlist.data.vo.BestsellerType
import com.delsi.bestsellerlist.data.vo.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookRepository @Inject constructor(private val appService: AppService) {

    suspend fun getBestSellerTypes(): Flow<Resource<List<BestsellerType>?>> =
        flow<Resource<List<BestsellerType>?>> {

            val apiResponse = appService.getTypes(apiKey = AppConst.API_KEY)

            if (apiResponse.isSuccessful) {
                emit(Resource.success(apiResponse.body()?.results))
            } else {
                emit(Resource.error(apiResponse.message()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getBookByType(type: String): Flow<Resource<List<Book>?>> =
        flow<Resource<List<Book>?>> {
            val apiResponse = appService.getBooks(type, apiKey = AppConst.API_KEY)

            if (apiResponse.isSuccessful) {
                emit(Resource.success(apiResponse.body()?.results?.books))
            } else {
                emit(Resource.error(apiResponse.message()))
            }
        }.flowOn(Dispatchers.IO)
}