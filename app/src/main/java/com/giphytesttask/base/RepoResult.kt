package com.giphytesttask.base

sealed interface RepoResult<T : Any, Error : Any>

class RepoSuccess<T : Any, Error : Any>(val data: T) : RepoResult<T, Error>
class RepoError<T : Any, Error : Any>(val data: Error) : RepoResult<T, Error>
class RepoException<T : Any, Error : Any>(val e: Throwable) : RepoResult<T, Error>

suspend fun <T : Any, Error : Any> RepoResult<T, Error>.onSuccess(
    executable: suspend (T) -> Unit
): RepoResult<T, Error> = apply {
    if (this is RepoSuccess<T, Error>) {
        executable(data)
    }
}

suspend fun <T : Any, Error : Any> RepoResult<T, Error>.onError(
    executable: suspend (Error) -> Unit
): RepoResult<T, Error> = apply {
    if (this is RepoError<T, Error>) {
        executable(data)
    }
}

suspend fun <T : Any, Error : Any> RepoResult<T, Error>.onException(
    executable: suspend (e: Throwable) -> Unit
): RepoResult<T, Error> = apply {
    if (this is RepoException<T, Error>) {
        executable(e)
    }
}

suspend fun <T : Any, C : Any, Error : Any> ApiResult<T>.mapToRepo(
    successMap: suspend (ApiSuccess<T>) -> C,
    errorMap: suspend (ApiError<T>) -> Error
): RepoResult<C, Error> {
    return when (this) {
        is ApiSuccess -> RepoSuccess(data = successMap(this))
        is ApiError -> RepoError(data = errorMap(this))
        is ApiException -> RepoException(this.e)
    }
}