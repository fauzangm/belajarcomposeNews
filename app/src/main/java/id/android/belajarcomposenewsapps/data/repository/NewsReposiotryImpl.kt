package id.android.belajarcomposenewsapps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.data.remote.ApiService
import id.android.belajarcomposenewsapps.data.remote.NewsPagingSource
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsReposiotryImpl(
    private val apiService : ApiService,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(apiService = apiService, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }
}