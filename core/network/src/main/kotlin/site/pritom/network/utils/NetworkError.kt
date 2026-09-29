package site.pritom.network.utils

/** Created by Pritom Dutta on 30/9/26 */
sealed interface NetworkError {
    data object NoInternet : NetworkError
    data object Timeout : NetworkError
    data class Unauthorized(val message: String) : NetworkError
    data class ServerError(val code: Int, val message: String) : NetworkError
    data class Unknown(val throwable: Throwable) : NetworkError
}

