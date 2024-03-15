package id.android.belajarcomposenewsapps.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.data.local.room.NewsDatabase
import id.android.belajarcomposenewsapps.data.local.room.NewsTypeConvertor
import id.android.belajarcomposenewsapps.data.pref.LocalUserPrefImpl
import id.android.belajarcomposenewsapps.data.pref.SessionManager
import id.android.belajarcomposenewsapps.data.pref.TokenHolder
import id.android.belajarcomposenewsapps.data.remote.ApiService
import id.android.belajarcomposenewsapps.data.remote.ApiServicePresensi
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenAuthenticator
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenInterceptor
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenProvider
import id.android.belajarcomposenewsapps.data.remote.authenticationPresensi.AccessTokenPresensiAuthenticator
import id.android.belajarcomposenewsapps.data.remote.authenticationPresensi.AccessTokenPresensiInterceptor
import id.android.belajarcomposenewsapps.data.remote.authenticationPresensi.AccessTokenPresensiProvider
import id.android.belajarcomposenewsapps.data.repository.auth.AuthRepositoryImpl
import id.android.belajarcomposenewsapps.data.repository.news.NewsReposiotryImpl
import id.android.belajarcomposenewsapps.domain.pref.LocalUserPref
import id.android.belajarcomposenewsapps.domain.repository.AuthRepository
import id.android.belajarcomposenewsapps.domain.repository.NewsRepository
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.AppEntryUseCases
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.ReadAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.SaveAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.news.GetNews
import id.android.belajarcomposenewsapps.domain.usecase.news.NewsUseCases
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.DeleteArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.GetArticles
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.SelectArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.UpsertArticle
import id.android.belajarcomposenewsapps.domain.usecase.presensi.PresensiUseCases
import id.android.belajarcomposenewsapps.domain.usecase.presensi.auth.LoginPresensi
import id.android.belajarcomposenewsapps.utils.Constans.BASE_URL
import id.android.belajarcomposenewsapps.utils.Constans.BASE_URL_PRESENSI
import id.android.belajarcomposenewsapps.utils.Constans.DB_NAME_NEWS
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val CONNECT_TIMEOUT: Long = 30
    private const val READ_TIMEOUT: Long = 30
    private const val WRITE_TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideLocalUserPref(
        application: Application
    ): LocalUserPref = LocalUserPrefImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserPref: LocalUserPref
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserPref),
        saveAppEntry = SaveAppEntry(localUserPref)
    )

    /** remote  News */


    @Provides
    @Singleton
    fun provideApiInstance(): ApiService {
        val apiService: ApiService
        val client = OkHttpClient.Builder()
        val tokenProvider = AccessTokenProvider("19a84b51d49a4fb39e93a10f58a13295")
        client
            .authenticator(AccessTokenAuthenticator(tokenProvider))
            .addInterceptor(AccessTokenInterceptor(tokenProvider))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        apiService = retrofit.create(ApiService::class.java)
        return apiService
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: ApiService,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsReposiotryImpl(apiService, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
//            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticle = SelectArticle(newsDao),
            getArticle = GetArticles(newsDao)
        )
    }


    /** remote  Presensi */
    @Provides
    @Singleton
    fun provideApiPresensiInstance(@ApplicationContext context: Context): ApiServicePresensi {
        val apiService: ApiServicePresensi
        val tokenHolder = TokenHolder(context)
        val sessionManager = SessionManager(context,tokenHolder)
        val client = OkHttpClient.Builder()
        val tokenProvider = AccessTokenPresensiProvider(sessionManager,tokenHolder)
        client
            .authenticator(AccessTokenPresensiAuthenticator(tokenProvider))
            .addInterceptor(AccessTokenPresensiInterceptor(tokenProvider))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_PRESENSI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        apiService = retrofit.create(ApiServicePresensi::class.java)
        return apiService
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiServicePresensi: ApiServicePresensi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AuthRepository {
        return AuthRepositoryImpl(apiServicePresensi,ioDispatcher)
    }

    @Provides
    @Singleton
    fun providePresensiUseCases(
        authRepository: AuthRepository,
    ): PresensiUseCases {
        return PresensiUseCases(
            login = LoginPresensi(authRepository)
        )
    }


    /** Locaal */
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = DB_NAME_NEWS
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}