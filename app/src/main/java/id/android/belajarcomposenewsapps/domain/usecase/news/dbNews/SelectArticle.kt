package id.android.belajarcomposenewsapps.domain.usecase.news.dbNews

import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticle(url = url)
    }

}