package id.android.belajarcomposenewsapps.data.remote.authenticationPresensi

import id.android.belajarcomposenewsapps.data.pref.SessionManager
import id.android.belajarcomposenewsapps.data.pref.TokenHolder
import id.android.belajarcomposenewsapps.data.remote.ApiServicePresensi
import id.android.belajarcomposenewsapps.utils.Constans
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class AccessTokenPresensiProvider(
    private val sessionManager: SessionManager,
    private val tokenHolder: TokenHolder
) {
    private val interceptor = HttpLoggingInterceptor()
    private val apiServices: ApiServicePresensi

    init {
//        if (BuildConfig.DEBUG) {
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        }
        val client = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL_PRESENSI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        apiServices = retrofit.create(ApiServicePresensi::class.java)
    }

    fun token(): String? {
        return tokenHolder.accessToken
    }

    fun refreshToken(): Boolean = runBlocking { tryRefresh() }

    private fun tryRefresh(): Boolean {
        //        try {
//            val response = apiServices.refreshAccessToken(tokenHolder.refreshToken.toString())
//            result = if (response.code() == 200) {
//                response.body()?.let { tokenHolder.storeToken(it) }
//                true
//            } else {
        sessionManager.clear()
//                false
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        return false
    }
}