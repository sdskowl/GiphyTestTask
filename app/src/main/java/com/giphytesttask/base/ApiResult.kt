package com.giphytesttask.base

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

sealed interface ApiResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
class ApiError<T : Any>(val code: Int, val message: String?, val body: ResponseBody?) : ApiResult<T>
class ApiException<T : Any>(val e: Throwable) : ApiResult<T>

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        } else {
            Log.e("ApiResult", "${response.errorBody()}")
            ApiError(
                code = response.code(),
                message = response.message(),
                body = response.errorBody()
            )
        }
    } catch (e: HttpException) {
        Log.e("ApiResult", "", e)
        ApiError(code = e.code(), message = e.message(), body = null)
    } catch (e: Throwable) {
        Log.e("ApiResult", "", e)
        ApiException(e)
    }
}


suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?, body: ResponseBody?) -> Unit
): ApiResult<T> = apply {
    if (this is ApiError<T>) {
        executable(code, message, body)
    }
}

suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): ApiResult<T> = apply {
    if (this is ApiException<T>) {
        executable(e)
    }
}