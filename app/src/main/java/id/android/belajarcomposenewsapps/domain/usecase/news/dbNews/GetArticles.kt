package id.android.belajarcomposenewsapps.domain.usecase.news.dbNews

import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles (
    private  val newsDao: NewsDao
){

     operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()
    }

}