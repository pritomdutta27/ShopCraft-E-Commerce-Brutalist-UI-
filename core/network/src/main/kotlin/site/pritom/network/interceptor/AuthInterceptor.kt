package site.pritom.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/** Created by Pritom Dutta on 30/9/26 */

class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = tokenProvider.getAccessToken()
        val requestBuilder = chain.request().newBuilder()
//        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer ")
//        }

        val response = chain.proceed(requestBuilder.build())
//        if (response.code == 403 || response.code == 401) {
//            tokenProvider.clearTokens()
//            authEventManager.logout()
//        }
        return response
    }
}