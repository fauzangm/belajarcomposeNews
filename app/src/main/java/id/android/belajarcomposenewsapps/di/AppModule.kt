package id.android.belajarcomposenewsapps.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.data.local.room.NewsDatabase
import id.android.belajarcomposenewsapps.data.local.room.NewsTypeConvertor
import id.android.belajarcomposenewsapps.data.pref.LocalUserPrefImpl
import id.android.belajarcomposenewsapps.data.remote.ApiService
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenAuthenticator
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenInterceptor
import id.android.belajarcomposenewsapps.data.remote.authentication.AccessTokenProvider
import id.android.belajarcomposenewsapps.data.repository.NewsReposiotryImpl
import id.android.belajarcomposenewsapps.domain.pref.LocalUserPref
import id.android.belajarcomposenewsapps.domain.repository.NewsRepository
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.AppEntryUseCases
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.ReadAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.SaveAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.news.GetNews
import id.android.belajarcomposenewsapps.domain.usecase.news.NewsUseCases
import id.android.belajarcomposenewsapps.utils.Constans.BASE_URL
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

    /** remote */


    @Provides
    @Singleton
    fun provideApiInstance(): ApiService {
        val apiService : ApiService
        val client =  OkHttpClient.Builder()
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
        return NewsReposiotryImpl(apiService,newsDao)
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
//            upsertArticle = UpsertArticle(newsDao),
//            deleteArticle = DeleteArticle(newsDao),
//            getArticles = GetArticles(newsDao),
//            getArticle = GetArticle(newsDao)
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
            name = "news_db"
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