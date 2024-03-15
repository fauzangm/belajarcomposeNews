package id.android.belajarcomposenewsapps.data.remote.authenticationPresensi

import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenPresensiInterceptor(
    private val tokenProvider: AccessTokenPresensiProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.token()

        return if (token == null) {
            chain.proceed(chain.request())
        } else {
            val authenticatedRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(authenticatedRequest)
        }
    }
}