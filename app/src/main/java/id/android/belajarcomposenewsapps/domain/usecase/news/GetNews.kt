package id.android.belajarcomposenewsapps.domain.usecase.news

import androidx.paging.PagingData
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}