package site.pritom.network.utils

import retrofit2.HttpException
import retrofit2.Response
import site.prito.dutta.core.common.NetworkError
import site.prito.dutta.core.common.NetworkResult
import site.pritom.network.interceptor.NoInternetException
import java.io.IOException
import java.net.SocketTimeoutException

/** Created by Pritom Dutta on 30/9/26 */

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(NetworkError.ServerError(response.code(), "Empty response body"))
            }
        } else {
            val code = response.code()
            val errorMsg = response.errorBody()?.string() ?: response.message()
            if (code == 401) {
                NetworkResult.Error(NetworkError.Unauthorized(errorMsg))
            } else {
                NetworkResult.Error(NetworkError.ServerError(code, errorMsg))
            }
        }
    } catch (e: NoInternetException) {
        NetworkResult.Error(NetworkError.NoInternet)
    } catch (e: SocketTimeoutException) {
        NetworkResult.Error(NetworkError.Timeout)
    } catch (e: HttpException) {
        if (e.code() == 401 || e.code() == 403) {
            NetworkResult.Error(NetworkError.Unauthorized(e.message()))
        } else {
            NetworkResult.Error(NetworkError.ServerError(e.code(), e.message()))
        }
    } catch (e: SecurityException) {
        // Handle encryption/decryption issues during the call (e.g. in Interceptors)
        NetworkResult.Error(NetworkError.Unauthorized("Security error: ${e.localizedMessage}"))
    } catch (e: IOException) {
        NetworkResult.Error(NetworkError.NoInternet)
    } catch (e: Exception) {
        NetworkResult.Error(NetworkError.Unknown(e))
    }
}