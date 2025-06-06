package com.syhan.cinemasearch.core.data.remote

import com.syhan.cinemasearch.core.domain.util.EmptyHttpBodyException
import com.syhan.cinemasearch.core.domain.util.NetworkError
import com.syhan.cinemasearch.core.domain.util.NetworkResult
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> makeNetworkRequest(
    request: suspend () -> Response<T>
) : NetworkResult<T> {
    return try {
        val response = request()
        val body = response.body() ?: throw EmptyHttpBodyException()

        if (!response.isSuccessful) throw HttpException(response)

        NetworkResult.Success(body)
    } catch (_: IOException) {
        NetworkResult.Error(NetworkError.NoInternet)
    } catch (_: EmptyHttpBodyException) {
        NetworkResult.Error(NetworkError.EmptyHttpBody)
    } catch (e: HttpException) {
        NetworkResult.Error(NetworkError.UnexpectedHttpResponse(e.code()))
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}
