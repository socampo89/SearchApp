package com.test.search.domain.repository

import com.google.gson.Gson
import com.test.search.presentation.networking.helpers.ErrorResponse
import com.test.search.presentation.networking.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
 
        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                 
                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    val errorResponse : ErrorResponse? = convertErrorBody(response.errorBody())
                    Resource.Error(errorMessage = errorResponse?.message ?: "Something went wrong")
                }
                 
            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            } 
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                return Gson().fromJson(it.readUtf8(), ErrorResponse::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }
}