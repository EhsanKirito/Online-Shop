package com.example.onlineshop.data.network.safeapicall

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>
): Flow<T> = flow {
    try {
        val response = apiCall()
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw UnSuccessfulResponseException(response.message())
        }
    } catch (e: java.lang.Exception) {
        throw SystemException(e.message.toString())
    }
}


class UnSuccessfulResponseException(message: String) : java.lang.Exception(message)
class SystemException(message: String) : java.lang.Exception(message)