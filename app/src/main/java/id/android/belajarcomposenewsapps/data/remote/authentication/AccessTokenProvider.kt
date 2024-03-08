package id.android.belajarcomposenewsapps.data.remote.authentication



class AccessTokenProvider(
    private val tokenHolder: String,
) {

//    private val interceptor = HttpLoggingInterceptor()
//    private val apiServices: AuthServices

//    init {
//        if(BuildConfig.DEBUG) {
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        }
//        val client = OkHttpClient.Builder()
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .retryOnConnectionFailure(true)
//            .cache(null)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL_AUTH)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client.build())
//            .build()
//        apiServices = retrofit.create(AuthServices::class.java)
//    }


    fun token(): String {
        return tokenHolder
    }

//    fun refreshToken(): Boolean = runBlocking { tryRefresh() }
//
//    private suspend fun tryRefresh(): Boolean {
//        Timber.e("Try Refreshing token")
//        var result = false
//        try {
//            val response = apiServices.refreshAccessToken(
//                "refresh_token",
//                tokenHolder.refreshToken.toString(),
//                BuildConfig.CLIENT_ID,
//                BuildConfig.CLIENT_SECRET,
//                ""
//            )
//            result = if (response.code() == 200) {
//                tokenHolder.storeToken(
//                    response.body()!!
//                )
//                true
//            } else {
//                if(sessionManager.isLoggedIn)
//                    sessionManager.logoutUser()
//                false
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return result
//    }
}