package id.android.belajarcomposenewsapps.data.remote

import id.android.belajarcomposenewsapps.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") source : String,
        @Query("from") from : String,
        @Query("sortBy") sortBy : String,
        @Query("page") page : Int,
    ):NewsResponse
}