package com.railian.mobile.githubusersmvvm.util.mvvm

import com.railian.mobile.githubusersmvvm.util.network.HttpResult

open class Repository {

    suspend fun <T : Any> makeHttpRequest(
        request: suspend () -> T
    ): HttpResult<T> {

        return try {
            val response = request.invoke()
            HttpResult.Success(response)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
    }
}