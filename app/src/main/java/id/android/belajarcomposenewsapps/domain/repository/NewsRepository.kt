package id.android.belajarcomposenewsapps.domain.repository

import androidx.paging.PagingData
import id.android.belajarcomposenewsapps.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}