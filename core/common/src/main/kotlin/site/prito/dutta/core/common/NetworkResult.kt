package site.prito.dutta.core.common

/** Created by Pritom Dutta on 30/9/26 */
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val error: NetworkError) : NetworkResult<Nothing>
}
