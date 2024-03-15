package id.android.belajarcomposenewsapps.data.remote

import id.android.belajarcomposenewsapps.data.remote.response.LoginResponse
import id.android.belajarcomposenewsapps.data.remote.response.NewsResponse
import id.android.belajarcomposenewsapps.domain.model.presensi.DataLoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServicePresensi {

    @POST("login")
    suspend fun login(
        @Query("email") email : String,
        @Query("password") password : String
    ): Response<LoginResponse>
}